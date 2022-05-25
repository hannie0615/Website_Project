package camper.project;

import camper.project.interceptor.LoginInterceptor;
import camper.project.repository.*;
import camper.project.service.CampService;
import camper.project.service.MemberService;
import camper.project.service.ReviewService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

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

    @Bean
    public CampService campService() { return new CampService(campRepositoryInterface()); }

    @Bean
    public CampRepositoryInterface campRepositoryInterface() {
        return new JdbcTemplateCampRepository(dataSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/myPage");
    }
    @Bean
    public ReviewService reviewService() {
        return new ReviewService(reviewRepositoryInterface());
    }

    @Bean
    public ReviewRepositoryInterface reviewRepositoryInterface() {
        return new JdbcTemplateReviewRepository(dataSource);
    }

}
