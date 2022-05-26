package camper.project.controller;

import camper.project.domain.Camp;
import camper.project.domain.Member;
import camper.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public String create(@Valid MemberForm member, Errors errors, Model model) {
        Member m = new Member();

        if (errors.hasErrors()) {
            model.addAttribute("member", member);

            Map<String, String> validatorResult = service.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "members/joinForm";
        }

        m.setBirthDate(member.getBirthDate());
        m.setName(member.getName());
        m.setId(member.getId());
        m.setPw(member.getPw());
        m.setType(member.getType());

        System.out.println(member.getType());
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
            System.out.println("실패");
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

        if(m.getType().equals("seller")) {
            List<Camp> campList = service.findCampsBySellerId(m.getId());

            model.addAttribute("registeredList", campList);

            return "members/sellerMyPage";
        }

        List<Reserve> r= reserveservice.findByid(m.getId());
        model.addAttribute("reserves", r);


        return "members/clientMyPage";


    }


}
