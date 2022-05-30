package camper.project.repository;

import camper.project.domain.Camp;
import camper.project.domain.Reserve;

import camper.project.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTempleteReserverepository implements ReserveRepositoryInterface {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcTempleteReserverepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public void saveReserve(Reserve r) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("reservation").usingGeneratedKeyColumns("reservedate");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("campname",r.getCampName());
        parameters.put("checkin",r.getCheckIn());
        parameters.put("checkout", r.getCheckOut());
        parameters.put("clientid",r.getClientId());
        parameters.put("roomid", r.getRoomId());
        parameters.put("roomname", r.getRoomName());
        jdbcInsert.execute(parameters);
    }

    public Camp findByCampId(int campId) {
        List<Camp> result = jdbcTemplate.query("SELECT * FROM camps WHERE campid = ?", campRowMapper(), campId);

        if (result.stream().findFirst().isPresent())
            return result.stream().findFirst().get();
        return null;
    }

    public Room findByRoomId(int roomId) {
        List<Room> result = jdbcTemplate.query("SELECT * FROM room WHERE roomid = ?", roomRowMapper(), roomId);

        if (result.stream().findFirst().isPresent())
            return result.stream().findFirst().get();
        return null;
    }

    @Override
    public List<Reserve> deletereserve(String reserveid) {
        jdbcTemplate.update("DELETE reserve WHERE reserveid = ?", reserveid);
        return jdbcTemplate.query("SELECT * FROM reserve where clientid = ?", reserveRowMapper(), reserveid);
    }

    @Override
    public List<Reserve> findByid(String clientid) {
        return jdbcTemplate.query("SELECT * FROM reserve where clientid = ?", reserveRowMapper(), clientid);

    }

    private RowMapper<Reserve> reserveRowMapper() {
        return new RowMapper<Reserve>() {
            @Override
            public Reserve mapRow(ResultSet rs, int rowNum) throws SQLException {
                Reserve r = new Reserve();
                r.setReserveDate(rs.getString("reservedate"));
                r.setCampName(rs.getString("campname"));
                r.setCheckIn(rs.getString("checkin"));
                r.setCheckOut(rs.getString("checkout"));
                r.setClientId(rs.getString("clientid"));
                r.setRoomName(rs.getString("roomname"));
                r.setRoomId(rs.getInt("roomid"));
                r.setReserveId(rs.getInt("reserveid"));
                return r;
            }
        };
    }

    private RowMapper<Camp> campRowMapper(){
        return new RowMapper<Camp>() {
            @Override
            public Camp mapRow(ResultSet rs, int rowNum) throws SQLException {
                Camp c = new Camp();

                c.setLocation(rs.getString("location"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setAddress(rs.getString("address"));
                c.setDate(rs.getString("postdate"));
                c.setSellerId(rs.getString("sellerId"));
                c.setCampId(rs.getInt("campId"));

                return c;
            }
        };
    }

    private RowMapper<Room> roomRowMapper() {
        return new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
                Room r = new Room();

                r.setPrice(rs.getString("price"));
                r.setRoomId(rs.getInt("roomid"));
                r.setCampId(rs.getInt("campid"));
                r.setName(rs.getString("name"));
                r.setReserveCheck(rs.getBoolean("reservecheck"));

                return r;
            }
        };
    }
}


