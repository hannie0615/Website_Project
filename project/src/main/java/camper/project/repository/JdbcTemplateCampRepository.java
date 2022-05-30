package camper.project.repository;

import camper.project.domain.Camp;
import camper.project.domain.CampImage;
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

public class JdbcTemplateCampRepository implements CampRepositoryInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateCampRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);


    }

    @Override
    public void saveCamp(Camp c) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("camps").usingGeneratedKeyColumns("postdate");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("location", c.getLocation());
        parameters.put("name", c.getName());
        parameters.put("address", c.getAddress());
        parameters.put("sellerId", c.getSellerId());

        jdbcInsert.execute(parameters);

    }

    @Override
    public Camp findCampByName(String name) {
        List<Camp> result = jdbcTemplate.query("SELECT * FROM camps WHERE name = ?", campRowMapper(), name);

        if (result.stream().findFirst().isPresent())
            return result.stream().findFirst().get();
        return null;


    }

    @Override
    public List<Camp> findCampByLocation(String location) {
        return jdbcTemplate.query("SELECT * FROM camps WHERE location = ?", campRowMapper(), location);
    }

    @Override
    public List<Camp> findCampBySellerId(String sellerId) {
        return jdbcTemplate.query("SELECT * FROM camps WHERE sellerId = ?", campRowMapper(), sellerId);
    }

    @Override
    public void saveImage(CampImage campImage) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("image");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", campImage.getName());
        parameters.put("uuid", campImage.getUuid());
        parameters.put("contenttype", campImage.getContentType());
        parameters.put("campid", campImage.getCampId());

        jdbcInsert.execute(parameters);

    }

    @Override
    public List<CampImage> findImg(String name) {
        return null;
    }


    @Override
    public void saveRoom(Room r) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("room").usingGeneratedKeyColumns("roomid");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", r.getName());
        parameters.put("price", r.getPrice());
        parameters.put("reservecheck", r.isReserveCheck());
        parameters.put("campid", r.getCampId());

        jdbcInsert.execute(parameters);
    }

    @Override
    public List<Room> findRoomByCampId(int campId) {
        return jdbcTemplate.query("SELECT * FROM room WHERE campid = ?", roomRowMapper(), campId);
    }

    @Override
    public void deleteCamp(int campId) {
        jdbcTemplate.update("DELETE camps WHERE campId = ?", campId);
    }

    @Override
    public Camp findCampByCampId(int campId) {
        List<Camp> result = jdbcTemplate.query("SELECT * FROM camps WHERE campid = ?", campRowMapper(), campId);

        if (result.stream().findFirst().isPresent())
            return result.stream().findFirst().get();
        return null;
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

    private RowMapper<CampImage> campImageRowMapper() {
        return new RowMapper<CampImage>() {
            @Override
            public CampImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                CampImage ci = new CampImage();


                ci.setName(rs.getString("name"));
                ci.setCampId(rs.getInt("campid"));
                ci.setUuid(rs.getString("uuid"));
                ci.setContentType(rs.getString("contenttype"));

                return ci;

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
