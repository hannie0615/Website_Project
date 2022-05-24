package camper.project.interceptor;

import camper.project.domain.Member;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Member loginMember = (Member)request.getSession().getAttribute("member");

        if (loginMember != null) {
            System.out.println(loginMember.getName());
            return true;
        } else {
            String destURI = request.getRequestURI();
            request.getSession().setAttribute("destURI", destURI);

            response.sendRedirect("/members/login");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
