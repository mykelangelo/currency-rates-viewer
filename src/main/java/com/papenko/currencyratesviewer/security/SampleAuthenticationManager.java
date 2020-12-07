package com.papenko.currencyratesviewer.security;

import com.papenko.currencyratesviewer.repository.RoleRepository;
import com.papenko.currencyratesviewer.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SampleAuthenticationManager implements AuthenticationManager {
    RoleRepository roleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication auth) {
        val user = userRepository.findByEmail(auth.getName());
        if (user == null || !passwordEncoder.matches((CharSequence) auth.getCredentials(), user.getHash())) {
            throw new BadCredentialsException("Bad Credentials");
        }
        return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),
                List.of(new SimpleGrantedAuthority(roleRepository.findByPrivileged(false).getName())));
    }
}
