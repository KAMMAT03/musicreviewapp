package com.musicreview.api.controllers;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.responses.ReviewResponse;
import com.musicreview.api.services.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("reviews/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDTO> createReview(HttpServletRequest request, @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.createReview(reviewDTO, request), HttpStatus.CREATED);
    }

    @GetMapping("reviews/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable(value = "reviewId") long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);
    }

    @GetMapping("albums/{albumId}/reviews")
    public ResponseEntity<ReviewResponse> getReviewsByAlbumId(@PathVariable(value = "albumId") String albumId,
                          @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(reviewService.getReviewsByAlbumId(albumId, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("users/{username}/reviews")
    public ResponseEntity<ReviewResponse> getReviewsByUserId(@PathVariable(value = "username") String username,
                                  @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(reviewService.getReviewsByUsername(username, pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("reviews/{reviewId}/update")
    public ResponseEntity<ReviewDTO> updateReview(HttpServletRequest request,
                                                  @PathVariable(value = "reviewId") long reviewId,
                                                  @RequestBody ReviewDTO reviewDTO
    ){

        return new ResponseEntity<>(reviewService.updateReview(reviewDTO, reviewId, request), HttpStatus.OK);
    }

    @DeleteMapping("reviews/{reviewId}/delete")
    public ResponseEntity<String> deleteReview(HttpServletRequest request,
                                               @PathVariable(value = "reviewId") long reviewId){
        reviewService.deleteReview(reviewId, request);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
