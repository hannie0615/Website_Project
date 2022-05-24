package camper.project.controller;

import camper.project.domain.Camp;
import camper.project.domain.CampImage;
import camper.project.domain.Member;
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
    public String register(@RequestParam MultipartFile[] uploadfile,HttpServletRequest request, CampForm campForm) throws IOException {
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
                CampImage ci = new CampImage(UUID.randomUUID().toString(),
                        file.getOriginalFilename(),
                        file.getContentType(),
                        service.findByName(campForm.getName()).getCampId());
                service.uploadImage(ci);

                File newFile = new File(ci.getUuid() + "_" + ci.getImgName());
                file.transferTo(newFile);
            }
        }

        return "redirect:myPage";

    }


    @GetMapping("registered")
    public String registered(HttpServletRequest request, Model model) {

        Member m = (Member)request.getSession().getAttribute("member");

        List<Camp> campList = service.findBySellerId(m.getId());
        model.addAttribute("registeredList", campList);

        return "camp/registeredList";
    }
}
