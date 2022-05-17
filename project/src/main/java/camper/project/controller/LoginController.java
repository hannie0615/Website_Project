package camper.project.controller;

import camper.project.domain.Member;
import camper.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("member")
@Controller
public class LoginController {

    private MemberService memberService;

    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model){
        Member m = memberService.findMember(id);

        if(m != null && m.getPw().equals(pw)) {
            model.addAttribute("member", m);
            return "redirect:/";
        } else {
            return "redirect:login";
        }
    }

}
