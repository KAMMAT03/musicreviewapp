package com.musicreview.api.services;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse getReviewsByAlbumId(String albumId, int pageNo, int pageSize);
    List<ReviewDTO> getReviewsByUserId(String userId, int pageNo, int pageSize);
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(ReviewDTO reviewDTO, long reviewId);
    void deleteReview(String reviewId);

}
