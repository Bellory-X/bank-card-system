package com.bank.management.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.management.user.impl.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: {}", username);

        User user = userRepository.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Update last login time
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        return new org.springframework.security.core.userdetails.User(
            user.getLogin(),
            user.getPassword(),
            user.isEnabled(),
            true,
            true,
            true,
            Collections.singletonList((new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}