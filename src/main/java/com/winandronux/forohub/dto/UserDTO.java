package com.winandronux.forohub.dto;

import com.winandronux.forohub.entity.Role;
import com.winandronux.forohub.entity.Topic;
import com.winandronux.forohub.entity.User;

public record UserDTO(Long Id, String username, String email, Role role, Integer numTopicsCreated, String[] topicsCreated, Integer numComments) {

    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getNumTopicsCreated(),
                user.getTopics().stream().map(Topic::getTitle).toList().toArray(new String[0]), user.getNumComments());
    }

}
