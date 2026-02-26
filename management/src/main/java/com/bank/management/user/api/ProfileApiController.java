package com.bank.management.user.api;

import java.util.UUID;

import com.bank.management.security.JwtUtils;
import com.bank.management.user.api.model.ChangePasswordRequest;
import com.bank.management.user.api.model.ProfileResponse;
import com.bank.management.user.api.model.UpdateProfileRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.management.user.impl.ProfileCommands;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class ProfileApiController {

    private final ProfileCommands service;
    private final JwtUtils jwtUtils;

    public ProfileResponse getProfile() {
        UUID userGuid = jwtUtils.getCurrentUserGuid();
        return service.get(userGuid);
    }

    public ProfileResponse updateProfile(
            UpdateProfileRequest updateProfileRequest
    ) {
        UUID userGuid = jwtUtils.getCurrentUserGuid();
        return service.add(userGuid, updateProfileRequest);
    }

    public void changePassword(
            ChangePasswordRequest changePasswordRequest
    ) {
        UUID userGuid = jwtUtils.getCurrentUserGuid();
        service.changePassword(userGuid, changePasswordRequest);
    }
}