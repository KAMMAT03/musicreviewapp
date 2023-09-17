package com.musicreview.api.services;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.responses.ReviewResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;
import java.util.List;

public interface ReviewService {
    ReviewResponse getReviewsByAlbumId(String albumId, int pageNo, int pageSize);
    List<ReviewDTO> getReviewsByUserId(String userId, int pageNo, int pageSize);
    ReviewDTO getReviewById(long reviewId);
    ReviewDTO createReview(ReviewDTO reviewDTO, HttpServletRequest request);
    ReviewDTO updateReview(ReviewDTO reviewDTO, long reviewId);
    void deleteReview(long reviewId);

}
