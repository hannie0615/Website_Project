package camper.project.controller;

import camper.project.domain.Member;
import camper.project.service.MemberService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@SessionAttributes("member")
public class MyPageController {



    MemberService memberService;

    @Autowired
    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }

    @GetMapping("/members/myPage")
    public String getMyPage(@ModelAttribute("member") Member m, Model model) throws IOException {

        if(m.getId() == null) {
            return "redirect:login";
        }
        model.addAttribute("member", m);
        return "members/myPageForm";





    }

}
