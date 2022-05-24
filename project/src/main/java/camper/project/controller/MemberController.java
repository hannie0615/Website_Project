package camper.project.controller;

import camper.project.domain.Member;
import camper.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MemberController {

    MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    // 회원가입
    @GetMapping("members/join")
    public String createMember() {
        return "members/joinForm";
    }

    @PostMapping("members/join")
    public String create(MemberForm memberForm) {
        Member m = new Member();
        m.setId(memberForm.getId());
        m.setPw(memberForm.getPw());
        m.setBirthDate(memberForm.getBirthDate());
        m.setName(memberForm.getName());

        service.join(m);

        return "redirect:/";
    }

    // 로그인 / 로그아웃
    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpServletRequest request, HttpServletResponse response, Model model){
        Member m = service.findMember(id);

        HttpSession session = request.getSession();
        String destURI = (String)session.getAttribute("destURI");

        if(m != null && m.getPw().equals(pw)) {
            session.setAttribute("member", m);
        } else {
            return "redirect:login";
        }

        if (destURI != null) {
            session.setAttribute("destURI", null);
            return "redirect:" + destURI;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";

    }

    // myPage
    @GetMapping("/myPage")
    public String getMyPage(HttpServletRequest request, Model model) throws IOException {

        Member m = (Member)request.getSession().getAttribute("member");

        model.addAttribute("loginMember", m);

        return "members/myPageForm";


    }


}
