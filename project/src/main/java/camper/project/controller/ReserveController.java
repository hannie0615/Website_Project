package camper.project.controller;

import camper.project.domain.Camp;
import camper.project.domain.Member;
import camper.project.domain.Reserve;
import camper.project.service.Reserveservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReserveController {

    Reserveservice reserveservice;

    @Autowired
    public ReserveController(Reserveservice reserveservice) {
        this.reserveservice = reserveservice;
    }
/*
    @GetMapping("Gangwon")
    public String Gangwon(Camp camp) {
        String loc = "강원";
        Camp

        camp.getLocation();
        return "#";
    }
*/
    @GetMapping("/camp/map")
    public String choicemap() {
        System.out.println("maplog");
        return "camp/map";
    }



    @GetMapping("/reserve/new")
    public String reservepage() {
        return"res/reservefrom";
    }

    @GetMapping("/reserve/look")
    public String lookreserve() {
        return "res/lookreserve";
    }

    @PostMapping("/reserve/new")
    public String create(HttpServletRequest request , Reserveform reserveform) {
        Reserve r= new Reserve();
        Member m = (Member)request.getSession().getAttribute("member");

        r.setClientname(m.getName());
        r.setClientid(m.getId());
        r.setStaytime(reserveform.getStaytime());
        r.setReserveplace(reserveform.getReserveplace());

        reserveservice.join(r);

        return "redirect:/";
    }

    @PostMapping("/reserve/find")
    public  String find(HttpServletRequest request, Model model) {
        System.out.println("find method in");
        Member m = (Member)request.getSession().getAttribute("member");
        List<Reserve> r= reserveservice.findByid(m.getId());
        model.addAttribute("reserves", r);
        return "res/findreserve";
    }

    @GetMapping ("/deletereserve")
    public String delete(@ModelAttribute() Reserve reserve, Model model) {
        System.out.println(reserve.getReserveid());
        List<Reserve> r = reserveservice.deletereserve(reserve.getReserveid());
        model.addAttribute("reserves", r);
        return "res/findreserve";

    }

}
