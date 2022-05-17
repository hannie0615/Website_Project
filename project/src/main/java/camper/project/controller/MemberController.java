package camper.project.controller;

import camper.project.domain.Member;
import camper.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

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


}
