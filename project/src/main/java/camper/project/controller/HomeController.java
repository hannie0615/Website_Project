package camper.project.controller;

import camper.project.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        Member m = (Member)request.getSession().getAttribute("member");

        if (m == null) {
            return "home";
        } else {
            model.addAttribute("userName", m.getName());
            return "loginHome";
        }

    }



}
