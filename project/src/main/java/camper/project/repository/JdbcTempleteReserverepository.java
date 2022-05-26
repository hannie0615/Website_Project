package camper.project.repository;

import camper.project.domain.Reserve;

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
        jdbcInsert.withTableName("reserve").usingGeneratedKeyColumns("reservedate", "reserveid");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("clientname",r.getClientname());
        parameters.put("clientid",r.getClientid());
        parameters.put("reserveplace",r.getReserveplace());
        parameters.put("staytime", r.getStaytime());
        jdbcInsert.execute(parameters);
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
                r.setClientname(rs.getString("clientname"));
                r.setClientid(rs.getString("clientid"));
                r.setReserveplace(rs.getString("reserveplace"));
                r.setStaytime(rs.getString("staytime"));
                r.setReservedate(rs.getString("reservedate"));
                r.setReserveid(rs.getString("reserveid"));
                return r;
            }
        };
    }
}


