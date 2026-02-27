package com.bank.management.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.bank.management.user.impl.UserCommands;
import com.bank.manager.user.api.model.CreateUserRequest;
import com.bank.manager.user.api.model.SortDirection;
import com.bank.manager.user.api.model.UpdateUserRequest;
import com.bank.manager.user.api.model.UserPageable;
import com.bank.manager.user.api.model.UserResponse;
import com.bank.manager.user.api.model.UserRole;
import com.bank.manager.user.api.model.UserSortField;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserApiController { // TODO Дописать

    private final UserCommands userService;

    public UserResponse createUser(@Valid CreateUserRequest request) {
        return userService.add(request);
    }

    public UserResponse updateUser(@Valid UpdateUserRequest request) {
        return userService.add(request);
    }

    public UserResponse getUser(UUID userGuid) {
        return userService.get(userGuid);
    }

    public UserPageable getUsers(
        @Min(0) @Valid Integer page, 
        @Min(1) @Max(100) @Valid Integer size,
        @Valid UserSortField sortBy, 
        @Valid SortDirection sortDirection, 
        @Valid UserRole role,
        @Valid Boolean enabled, 
        @Size(min = 2) @Valid String searchQuery) {
        // TODO Auto-generated method stub
        return userService.get(
            new UserFilter(
                searchQuery, 
                searchQuery, 
                searchQuery, 
                searchQuery, 
                searchQuery, 
                searchQuery, 
                role, 
                enabled, 
                null, 
                page, 
                size, 
                sortBy, 
                null)
        );
    }
}