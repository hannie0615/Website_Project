package camper.project.repository;

import camper.project.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateReviewRepository implements ReviewRepositoryInterface{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateReviewRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void registerReview(Review r) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("review").usingGeneratedKeyColumns("reviewid");

        Map<String, Object> parameters = new HashMap<>();
        // 임의로 다 받는 것으로 일단 설정.
        parameters.put("name", r.getName());
        parameters.put("id", r.getId());
        parameters.put("title", r.getTitle());
        parameters.put("detail", r.getDetail());
        parameters.put("campId", r.getCampId());
        parameters.put("postdate", r.getPostdate());


        //jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        jdbcInsert.execute(parameters);
    }

    @Override
    public Review findReviewById(String id) {
        return null;
    }

    @Override
    public List<Review> findReviewAll() {
        return null;
    }


}
