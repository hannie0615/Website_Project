package camper.project.repository;

import javax.sql.DataSource;

import camper.project.domain.Member;
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

        jdbcInsert.execute(parameters);

    }

    @Override
    public Member findMember(String id) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper(), id);

        if (result.stream().findFirst().isPresent())
            return result.stream().findFirst().get();
        return null;
    }

    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member m = new Member();

                m.setId(rs.getString("id"));
                m.setPw(rs.getString("pw"));
                m.setName(rs.getString("name"));
                m.setBirthDate(rs.getInt("birthdate"));

                return m;
            }
        };
    }
}
