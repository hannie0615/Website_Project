package camper.project;

import camper.project.repository.JdbcTemplateMemberRepository;
import camper.project.repository.MemberRepositoryInterface;
import camper.project.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepositoryInterface());
    }

    @Bean
    public MemberRepositoryInterface memberRepositoryInterface() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
