package com.musicreview.api.services;

import com.musicreview.api.dto.UserDTO;

public interface UserService {
    UserDTO getUserByUsername(String username);
}
