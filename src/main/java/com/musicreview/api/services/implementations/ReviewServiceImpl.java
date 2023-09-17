package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.exceptions.CustomAuthorisationException;
import com.musicreview.api.models.UserEntity;
import com.musicreview.api.repositories.UserRepository;
import com.musicreview.api.responses.ReviewResponse;
import com.musicreview.api.exceptions.ReviewNotFoundException;
import com.musicreview.api.models.Review;
import com.musicreview.api.repositories.ReviewRepository;
import com.musicreview.api.security.TokenGenerator;
import com.musicreview.api.services.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.musicreview.api.security.JWTAuthenticationFilter.getJWTFromRequest;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final TokenGenerator tokenGenerator;
    private final UserRepository userRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, TokenGenerator tokenGenerator,
                             UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.tokenGenerator = tokenGenerator;
        this.userRepository = userRepository;
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
    public ReviewResponse getReviewsByUsername(String username, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        UserEntity userEntity = userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("There is no account with such username!"));
        long userId = userEntity.getId();

        Page<Review> reviews = reviewRepository.findAllByUserId(userId, pageable);
        List<Review> reviewList = reviews.getContent();
        List<ReviewDTO> content = reviewList.stream().map(this::mapToDTO).toList();

        ReviewResponse reviewResponse = mapToResponse(reviews);
        reviewResponse.setContent(content);

        return reviewResponse;
    }

    @Override
    public ReviewDTO getReviewById(long reviewId) {
        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ReviewNotFoundException("There is no review with this id"));

        return mapToDTO(review);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO, HttpServletRequest request) {
        reviewDTO.setDateOfPublication(LocalDateTime.now());

        Review review = mapToEntity(reviewDTO);

        String token = getJWTFromRequest(request);
        String username = tokenGenerator.getUsernameFromJWT(token);

        UserEntity user = userRepository.findByUsername(username).
                    orElseThrow(() -> new UsernameNotFoundException("This user does not exist"));

        review.setUser(user);

        Review newReview = reviewRepository.save(review);

        return mapToDTO(newReview);
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDTO, long reviewId, HttpServletRequest request) {
        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ReviewNotFoundException("Could not update this review because it does not exist"));

        String token = getJWTFromRequest(request);
        String username = tokenGenerator.getUsernameFromJWT(token);

        if (!review.getUser().getUsername().equals(username))
            throw new CustomAuthorisationException("You are not authorised to edit this review!");

        if (reviewDTO.getScore() != 0) review.setScore(reviewDTO.getScore());
        if (reviewDTO.getContent() != null) review.setContent(reviewDTO.getContent());
        if (reviewDTO.getTitle() != null) review.setTitle(reviewDTO.getTitle());

        Review updatedReview = reviewRepository.save(review);

        return mapToDTO(updatedReview);
    }

    @Override
    public void deleteReview(long reviewId, HttpServletRequest request) {
        Review review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ReviewNotFoundException("Could not delete this review because it does not exist"));

        String token = getJWTFromRequest(request);
        String username = tokenGenerator.getUsernameFromJWT(token);

        if (!review.getUser().getUsername().equals(username))
            throw new CustomAuthorisationException("You are not authorised to edit this review!");

        reviewRepository.delete(review);
    }

    private ReviewDTO mapToDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setLikes(review.getLikes());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setScore(review.getScore());
        reviewDTO.setUsername(review.getUser().getUsername());
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
