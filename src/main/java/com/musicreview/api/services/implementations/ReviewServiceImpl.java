package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.dto.ReviewResponse;
import com.musicreview.api.exceptions.ReviewNotFoundException;
import com.musicreview.api.models.Review;
import com.musicreview.api.repositories.ReviewRepository;
import com.musicreview.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewResponse getReviewsByAlbumId(String albumId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Review> reviews = reviewRepository.findAllByAlbumId(albumId, pageable);
        List<Review> reviewList = reviews.getContent();
        List<ReviewDTO> content = reviewList.stream().map(this::mapToDTO).toList();

        ReviewResponse reviewResponse = mapToResponse(reviews);
        reviewResponse.setContent(content);

        return reviewResponse;
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(String userId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        reviewDTO.setDateOfPublication(LocalDateTime.now());
        Review review = reviewRepository.save(mapToEntity(reviewDTO));
        return mapToDTO(review);
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDTO, long reviewId) {
        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ReviewNotFoundException("Could not update this review because it does not exist"));

        if (reviewDTO.getScore() != 0) review.setScore(reviewDTO.getScore());
        if (reviewDTO.getContent() != null) review.setContent(reviewDTO.getContent());
        if (reviewDTO.getTitle() != null) review.setTitle(reviewDTO.getTitle());

        Review updatedReview = reviewRepository.save(review);

        return mapToDTO(updatedReview);
    }

    @Override
    public void deleteReview(String reviewId) {

    }

    private ReviewDTO mapToDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setLikes(review.getLikes());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setScore(review.getScore());
        reviewDTO.setAlbumId(review.getAlbumId());
        reviewDTO.setDateOfPublication(review.getDateOfPublication());
        reviewDTO.setTitle(review.getTitle());
        return reviewDTO;
    }

    private Review mapToEntity(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setLikes(reviewDTO.getLikes());
        review.setContent(reviewDTO.getContent());
        review.setScore(reviewDTO.getScore());
        review.setAlbumId(reviewDTO.getAlbumId());
        review.setDateOfPublication(reviewDTO.getDateOfPublication());
        review.setTitle(reviewDTO.getTitle());
        return review;
    }

    private ReviewResponse mapToResponse(Page<Review> reviews){
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setPageNo(reviews.getNumber());
        reviewResponse.setPageSize(reviews.getSize());
        reviewResponse.setTotalElements(reviews.getTotalElements());
        reviewResponse.setTotalPages(reviews.getTotalPages());
        reviewResponse.setLast(reviews.isLast());
        return reviewResponse;
    }
}
