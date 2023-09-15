package com.musicreview.api.services;

import com.musicreview.api.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviewsByAlbumId(String AlbumId);
    List<ReviewDTO> getReviewsByUserId(String UserId);
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(ReviewDTO reviewDTO, String reviewId);
    void deleteReview(String reviewId);

}
