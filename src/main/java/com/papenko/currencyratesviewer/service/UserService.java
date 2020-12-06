package com.papenko.currencyratesviewer.service;

import com.papenko.currencyratesviewer.dto.UserDto;
import com.papenko.currencyratesviewer.entity.User;
import com.papenko.currencyratesviewer.repository.RoleRepository;
import com.papenko.currencyratesviewer.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public void persistFreeUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Caught you trying to change password, upgrade to paid plan for that!");
        }
        val hash = passwordEncoder.encode(userDto.getHash());
        val role = roleRepository.findByPrivileged(false);
        @Valid User user = new User(userDto.getEmail(), hash, role);
        userRepository.save(user);
    }

    public void authenticate(HttpServletRequest req, UserDto user) {
        val authReq = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getHash());
        Authentication auth = authenticationManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }
}
