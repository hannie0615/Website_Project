package camper.project.controller;

import camper.project.domain.Camp;
import camper.project.domain.CampImage;
import camper.project.domain.Member;
import camper.project.domain.Room;
import camper.project.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CampController {

    CampService service;

    @Autowired
    public CampController(CampService service) {
        this.service = service;
    }


    @GetMapping("register")
    public String registerForm() {
        return "camp/registerForm";
    }

    @Value("${spring.servlet.multipart.location}")
    String imgpath;

    @PostMapping("register")
    public String register(@RequestParam MultipartFile[] uploadfile,HttpServletRequest request, CampForm campForm, Model model) throws IOException {
        Camp c = new Camp();

        Member m = (Member)request.getSession().getAttribute("member");

        c.setName(campForm.getName());
        c.setLocation(campForm.getLocation());
        c.setAddress(campForm.getAddress());
        c.setSellerId(m.getId());

        List<CampImage> list = new ArrayList<>();
        service.register(c);

        for (MultipartFile file : uploadfile) {
            if (!file.isEmpty()) {
                CampImage ci = new CampImage("main",
                        UUID.randomUUID().toString(),
                        file.getContentType(),
                        service.findByName(campForm.getName()).getCampId());
                service.uploadImage(ci);

                File newFile = new File(ci.getUuid());
                file.transferTo(newFile);
            }
        }
        c.setCampId(service.findByName(campForm.getName()).getCampId());
        model.addAttribute("registeringCamp", c);
        // 변경내

        return "camp/registerRoom";

    }

    @PostMapping("camp/drop")
    public String dropCamp(@RequestParam("campId") int campId) {
        service.deleteCamp(campId);

        return "redirect:/myPage";

    }



    @PostMapping("registerRoom")
    public String registerRoom(@RequestParam MultipartFile[] uploadfile, RoomForm r, HttpServletRequest request, CampForm campForm, Model model) throws IOException {

        Room room = new Room();

        System.out.println(r.getCampId());
        System.out.println(r.getName());
        System.out.println(r.getPrice());
        System.out.println(r.getRoomId());

        for (MultipartFile file : uploadfile) {
            if (!file.isEmpty()) {
                CampImage ci = new CampImage(r.getName(),
                        UUID.randomUUID().toString(),
                        file.getContentType(),
                        r.getCampId());
                service.uploadImage(ci);

                File newFile = new File(ci.getUuid());
                file.transferTo(newFile);
            }
        }

        room.setRoomId(r.getRoomId());
        room.setCampId(r.getCampId());
        room.setReserveCheck(false);
        room.setName(r.getName());
        room.setPrice(r.getPrice());

        service.registerRoom(room);
        model.addAttribute("registeringCamp", room);

        return "camp/registerRoom";

    }

    @GetMapping("registered")
    public String registered(HttpServletRequest request, Model model) {

        Member m = (Member)request.getSession().getAttribute("member");

        List<Camp> campList = service.findBySellerId(m.getId());
        model.addAttribute("registeredList", campList);

        return "camp/registeredList";
    }

    @PostMapping("search")
    public String search(@RequestParam("name") String name, Model model) {

        Camp c = service.findByName(name);
        model.addAttribute(c);
        return "camp/campDetail";


    }

    @GetMapping("camp/seoul")
    public String seoul(Camp camp, Model model) {
        String loc = "서울/경기";
        List<Camp> campList = service.findByLocation(loc);

        model.addAttribute("campList", campList);
        return "camp/campList";
    }

    @GetMapping("camp/gangwon")
    public String Gangwon(Camp camp, Model model) {
        String loc = "강원도";
        List<Camp> campList = service.findByLocation(loc);
        model.addAttribute("campList", campList);
        return "camp/campList";
    }

    @GetMapping("camp/chungcheong")
    public String Chungcheong(Camp camp, Model model) {
        String loc = "충청도";
        List<Camp> campList = service.findByLocation(loc);

        model.addAttribute("campList", campList);
        return "camp/campList";
    }

    @GetMapping("camp/kyeongsang")
    public String kyeongsang(Camp camp, Model model) {
        String loc = "경상도";
        List<Camp> campList = service.findByLocation(loc);

        model.addAttribute("campList", campList);
        return "camp/campList";
    }

    @GetMapping("camp/jeonlado")
    public String jeonlado(Camp camp, Model model) {
        String loc = "전라도";
        List<Camp> campList = service.findByLocation(loc);

        model.addAttribute("campList", campList);
        return "camp/campList";
    }

    @GetMapping("camp/campDetail")
    public String campDetail(@RequestParam("campId") int campId, Model model) {


        List<Room> roomList = new ArrayList<>();
        for (Room room : service.findRoomByCampId(campId)) {
            if (!room.isReserveCheck()) {
                roomList.add(room);
            }
        }
        Camp c = service.findCampByCampId(campId);

        model.addAttribute("roomList", roomList);
        model.addAttribute("camp", c);

        return "camp/campDetail";
    }





    // Seller mypage


}
