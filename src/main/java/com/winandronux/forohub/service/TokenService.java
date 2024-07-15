package com.winandronux.forohub.service;

import com.winandronux.forohub.entity.User;

public interface TokenService {
    String getToken(User user);
    String getSubject(String token);
}
