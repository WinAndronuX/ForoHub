package com.winandronux.forohub.service;

import com.winandronux.forohub.dto.UserAddDTO;
import com.winandronux.forohub.dto.UserDTO;
import com.winandronux.forohub.dto.UserUpdatePasswordDTO;

public interface UserService {
    UserDTO getUser(Long id);
    UserDTO createUser(UserAddDTO userAddDTO);
    UserDTO updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO);
    void deleteUser(Long id);
}
