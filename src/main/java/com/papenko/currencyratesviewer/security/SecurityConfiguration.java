package com.papenko.currencyratesviewer.security;

import com.papenko.currencyratesviewer.repository.RoleRepository;
import com.papenko.currencyratesviewer.repository.UserRepository;
import com.papenko.currencyratesviewer.service.MyUserDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    MyUserDetailsService userDetailsService;
    RoleRepository roleRepository;
    UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
                .mvcMatchers("/", "/register", "/login")
                    .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
            .httpBasic();
        // @formatter:on
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return new SampleAuthenticationManager(roleRepository, userRepository, bCryptPasswordEncoder());
    }
}
