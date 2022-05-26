package camper.project.controller;

import camper.project.domain.Review;
import camper.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {

    ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("reviews/new")
    public String createReview() {
        return "reviews/ReviewPage";
    }

    // URL 안바뀐 상태에서 저장 하고 인덱스로 가는 거
    @PostMapping("reviews/new")
    public String create(@RequestParam ReviewForm reviewForm) {
        // 우리는 사용자가 입력한 name 값을 가지고 객체를 만들어요
        Review r = new Review();

        r.setName(reviewForm.getName());
        r.setId(reviewForm.getId());
        r.setTitle(reviewForm.getTitle());
        r.setDetail(reviewForm.getDetail());
        r.setCampId(reviewForm.getCampId());
        r.setPostdate(reviewForm.getPostdate());

        // DB에 넣기(postdate와 id는 자동 생성)
        service.register(r);

        return "redirect:/"; // 제일 첫 페이지로 돌아감
    }

    @GetMapping("reviews/find")
    public String findReview() {
        return "reviews/modificationPage";
    }

    @PostMapping("reviews/find")
    public String find(@RequestParam("id") String id, Model model) {
        // Service를 통해서 고객의 id로 찾아서
        Review r = service.findReview(id);

        // 찾은 객체를 통째로 다음 페이지로 넘겨요
        model.addAttribute("review", r);

        // 다음 페이지로 이동!
        return "reviews/modificationPage";
    }

    @GetMapping("reviews") // localhost:8080/members
    public String ReviewList(Model model) {
        List<Review> reviews = service.findAll();
        model.addAttribute("reviews", reviews);
        return "reviews/reviewBoard";
    }
}