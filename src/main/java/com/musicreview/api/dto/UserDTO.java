package com.musicreview.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String password;
    private List<ReviewDTO> reviews;
}
