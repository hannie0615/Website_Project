package camper.project.controller;

import camper.project.domain.Camp;
import camper.project.domain.Member;
import camper.project.domain.Reserve;
import camper.project.domain.Room;
import camper.project.service.Reserveservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ReserveController {

    Reserveservice reserveservice;

    @Autowired
    public ReserveController(Reserveservice reserveservice) {
        this.reserveservice = reserveservice;
    }



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


   @GetMapping("/onlyClient")
   public String onlyClient() {
        return "/onlyClient";
   }


    @PostMapping("camp/reserve")
    public String reserve(@RequestParam("roomId") int roomId, @RequestParam("checkIn") String checkIn, @RequestParam("checkOut") String checkOut, HttpServletRequest request) {
        System.out.println(checkOut);
        System.out.println(checkIn);
        System.out.println(roomId);

        Reserve r = new Reserve();

        Member m = (Member)request.getSession().getAttribute("member");
        Room room = reserveservice.findRoomByRoomId(roomId);
        Camp camp = reserveservice.findCampByCampId(room.getCampId());

        r.setRoomId(roomId);
        r.setRoomName(room.getName());
        r.setClientId(m.getId());
        r.setCheckIn(checkIn);
        r.setCheckOut(checkOut);
        r.setCampName(camp.getName());

        reserveservice.join(r);

        return "redirect:/";
    }

}
