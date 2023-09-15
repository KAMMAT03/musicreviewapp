package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.exceptions.ReviewNotFoundException;
import com.musicreview.api.models.Review;
import com.musicreview.api.repositories.ReviewRepository;
import com.musicreview.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ReviewDTO> getReviewsByAlbumId(String albumId) {

        return null;
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(String UserId) {
        return null;
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        reviewDTO.setDateOfPublication(LocalDateTime.now());
        Review review = reviewRepository.save(mapToEntity(reviewDTO));
        return mapToDTO(review);
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDTO, String reviewId) {
        return null;
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
}
