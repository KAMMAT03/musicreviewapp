package com.musicreview.api.controllers;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.dto.ReviewResponse;
import com.musicreview.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("create-review")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.createReview(reviewDTO), HttpStatus.CREATED);
    }

    @GetMapping("{albumId}/reviews")
    public ResponseEntity<ReviewResponse> getReviewsByAlbumId(@PathVariable(value = "albumId") String albumId,
                          @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(reviewService.getReviewsByAlbumId(albumId, pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("{reviewId}/update")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable(value = "reviewId") long reviewId,
                                                  @RequestBody ReviewDTO reviewDTO
    ){
        return new ResponseEntity<>(reviewService.updateReview(reviewDTO, reviewId), HttpStatus.OK);
    }
}
