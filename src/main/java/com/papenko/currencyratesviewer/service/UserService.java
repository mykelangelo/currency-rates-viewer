package com.papenko.currencyratesviewer.service;

import com.papenko.currencyratesviewer.dto.UserDto;
import com.papenko.currencyratesviewer.entity.User;
import com.papenko.currencyratesviewer.repository.RoleRepository;
import com.papenko.currencyratesviewer.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public void persistFreeUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Caught you trying to change password, upgrade to paid plan for that!");
        }
        val hash = passwordEncoder.encode(userDto.getHash());
        val role = roleRepository.findByPrivileged(false);
        User user = new User(userDto.getEmail(), hash, role);
        userRepository.save(user);
    }
}
