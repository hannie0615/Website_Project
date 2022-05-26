package camper.project.repository;

import javax.sql.DataSource;

import camper.project.domain.Camp;
import camper.project.domain.Member;
import camper.project.domain.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateMemberRepository implements MemberRepositoryInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveMember(Member m) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", m.getName());
        parameters.put("id", m.getId());
        parameters.put("pw", m.getPw());
        parameters.put("birthdate", m.getBirthDate());
        parameters.put("type", m.getType());

        jdbcInsert.execute(parameters);

    }

    @Override
    public Member findMember(String id) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper(), id);

        if (result.stream().findFirst().isPresent())
            return result.stream().findFirst().get();
        return null;
    }

    @Override
    public List<Camp> findCampsBySellerId(String id) {
        return jdbcTemplate.query("SELECT * FROM camps WHERE sellerid = ?", campRowMapper(), id);
    }

    @Override
    public List<Reserve> findByid(String id) {
        return jdbcTemplate.query("SELECT * FROM reserve WHERE id=?", reserveRowMapper(), id);
    }


    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member m = new Member();

                m.setId(rs.getString("id"));
                m.setPw(rs.getString("pw"));
                m.setName(rs.getString("name"));
                m.setBirthDate(rs.getString("birthdate"));
                m.setType(rs.getString("type"));

                return m;
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
