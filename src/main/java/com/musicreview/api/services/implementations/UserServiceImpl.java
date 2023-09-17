package com.musicreview.api.services.implementations;

import com.musicreview.api.dto.ReviewDTO;
import com.musicreview.api.dto.UserDTO;
import com.musicreview.api.models.Review;
import com.musicreview.api.models.UserEntity;
import com.musicreview.api.repositories.UserRepository;
import com.musicreview.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("There is no account with such username!"));

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setReviews(userEntity.getReviews().stream().map(this::mapToDto).collect(Collectors.toList()));

        return userDTO;
    }

    private ReviewDTO mapToDto(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setId(review.getId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setScore(review.getScore());
        reviewDTO.setLikes(review.getLikes());
        reviewDTO.setDateOfPublication(review.getDateOfPublication());
        reviewDTO.setAlbumId(review.getAlbumId());

        return reviewDTO;
    }
}
