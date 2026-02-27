package com.bank.management.user.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import com.bank.management.user.impl.model.User;
import com.bank.manager.user.api.model.CreateUserRequest;
import com.bank.manager.user.api.model.ProfileResponse;
import com.bank.manager.user.api.model.UpdateProfileRequest;
import com.bank.manager.user.api.model.UpdateUserRequest;
import com.bank.manager.user.api.model.UserPageable;
import com.bank.manager.user.api.model.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {// TODO Дописать

    @Mapping(source = "guid", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "createdAt", target = "createdAt")
    public ProfileResponse toProfileResponse(User user);

    @Mapping(source = "guid", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "lastLoginAt", target = "lastLoginAt")
    public UserResponse toUserResponse(User user);
    
    public UserPageable toUserPageable(Page<User> page);

    public User toUser(CreateUserRequest request);

    @Mapping(source = "guid", target = "guid")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "lastLoginAt", target = "lastLoginAt")
    public void changeUser(@MappingTarget User user, UpdateUserRequest request);

    @Mapping(source = "guid", target = "guid")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "lastLoginAt", target = "lastLoginAt")
    public void changeUser(@MappingTarget User user, UpdateProfileRequest request);
}