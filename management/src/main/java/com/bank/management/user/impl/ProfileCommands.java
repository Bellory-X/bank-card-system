package com.bank.management.user.impl;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.management.user.exception.InvalidPasswordProfileException;
import com.bank.management.user.impl.mapper.UserMapper;
import com.bank.management.user.impl.model.User;
import com.bank.management.user.impl.repository.UserRepository;
import com.bank.manager.user.api.model.ChangePasswordRequest;
import com.bank.manager.user.api.model.ProfileResponse;
import com.bank.manager.user.api.model.UpdateProfileRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileCommands {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ProfileResponse get(UUID userGuid) {
        User user = repository.getByUserGuid(userGuid);
        return mapper.toProfileResponse(user);
    }

    @Transactional
    public ProfileResponse add(UUID userGuid, UpdateProfileRequest request) {
        User user = repository.getByUserGuid(userGuid);
        mapper.changeUser(user, request);
        user = repository.save(user);
        return mapper.toProfileResponse(user);
    }

    @Transactional
    public void changePassword(UUID userGuid, ChangePasswordRequest request) {
        User user = repository.getByUserGuid(userGuid);
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new InvalidPasswordProfileException();
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
    }
}
