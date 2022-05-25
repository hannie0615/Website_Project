package camper.project.repository;

import camper.project.domain.Review;

import java.util.List;

public interface ReviewRepositoryInterface {

    void registerReview(Review r); // 리뷰 등록하기
    Review findReviewById(String id); // customer id를 통해 리뷰 찾기
    List<Review> findReviewAll(); // 모든 리뷰 찾기
}
