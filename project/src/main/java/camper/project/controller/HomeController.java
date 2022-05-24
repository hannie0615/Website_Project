package camper.project.controller;

import camper.project.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
