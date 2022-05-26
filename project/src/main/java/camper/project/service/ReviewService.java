package camper.project.service;

import camper.project.domain.Review;
import camper.project.repository.ReviewRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ReviewService {

    private ReviewRepositoryInterface repository;

    @Autowired
    public ReviewService(ReviewRepositoryInterface repository){
        this.repository = repository;
    }

    public void register(Review r) {
        repository.registerReview(r);
    }

    public Review findReview(String id) {
        return repository.findReviewById(id);
    }

    public List<Review> findAll() {
        return repository.findReviewAll();
    }
}
