package com.winandronux.forohub.service.impl;

import com.winandronux.forohub.dto.UserAddDTO;
import com.winandronux.forohub.dto.UserDTO;
import com.winandronux.forohub.dto.UserUpdatePasswordDTO;
import com.winandronux.forohub.entity.User;
import com.winandronux.forohub.repository.UserRepository;
import com.winandronux.forohub.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUser(Long id) {
        var user = userRepository.findById(id);
        return user.map(UserDTO::new).orElse(null);
    }

    @Override
    @Transactional
    public UserDTO createUser(UserAddDTO userAddDTO) {
        if (userRepository.existsByUsername(userAddDTO.username()))
            throw new EntityExistsException("A User with the userId " + userAddDTO.username() + " already exists");
        if (userRepository.existsByEmail(userAddDTO.email()))
            throw new EntityExistsException("A User with the email " + userAddDTO.email() + " already exists");

        var user = new User(userAddDTO);
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {
        var optionalUser = userRepository.findById(userUpdatePasswordDTO.Id());
        if (optionalUser.isEmpty()) return null;

        var user = optionalUser.get();
        user.setPassword(userUpdatePasswordDTO.password());

        return new UserDTO(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent())
            userRepository.delete(user.get());
        else
            throw new EntityNotFoundException("User with id " + id + " does not exist");
    }
}
