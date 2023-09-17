package com.musicreview.api.services;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.responses.ReviewResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ReviewService {
    ReviewResponse getReviewsByAlbumId(String albumId, int pageNo, int pageSize);
    ReviewResponse getReviewsByUsername(String username, int pageNo, int pageSize);
    ReviewDTO getReviewById(long reviewId);
    ReviewDTO createReview(ReviewDTO reviewDTO, HttpServletRequest request);
    ReviewDTO updateReview(ReviewDTO reviewDTO, long reviewId, HttpServletRequest request);
    void deleteReview(long reviewId, HttpServletRequest request);

}
