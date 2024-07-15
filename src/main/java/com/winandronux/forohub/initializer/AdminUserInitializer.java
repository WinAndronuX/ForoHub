package com.winandronux.forohub.initializer;

import com.winandronux.forohub.dto.UserAddDTO;
import com.winandronux.forohub.entity.Role;
import com.winandronux.forohub.entity.User;
import com.winandronux.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public AdminUserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            var userAddDTO = new UserAddDTO("admin", "admin@example.com", "admin", String.valueOf(Role.ADMIN));

            userRepository.save(new User(userAddDTO));
        }
    }
}
