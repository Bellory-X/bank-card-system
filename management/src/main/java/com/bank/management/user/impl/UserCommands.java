package com.bank.management.user.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.management.user.impl.mapper.UserMapper;
import com.bank.management.user.impl.mapper.UserSpecification;
import com.bank.management.user.impl.model.User;
import com.bank.management.user.impl.repository.UserRepository;
import com.bank.manager.user.api.model.CreateUserRequest;
import com.bank.manager.user.api.model.UpdateUserRequest;
import com.bank.manager.user.api.model.UserPageable;
import com.bank.manager.user.api.model.UserResponse;

import java.util.UUID;

import com.bank.management.user.api.UserFilter;
import com.bank.management.user.exception.NotFoundUserException;

@Service
@RequiredArgsConstructor
public class UserCommands {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public UserPageable get(UserFilter filter) {
        Pageable pageable = UserSpecification.getPageable(filter);
        Specification<User> spec = UserSpecification.withFilters(filter);
        Page<User> users = repository.findAll(spec, pageable);
        return mapper.toUserPageable(users);
    }

    @Transactional
    public UserResponse get(UUID userGuid) {
        User user = repository.getByUserGuid(userGuid);
        return mapper.toUserResponse(user);
    }

    @Transactional
    public UserResponse add(CreateUserRequest request) {
        User user = mapper.toUser(request);
        user = repository.save(user);
        return mapper.toUserResponse(user);
    }


    @Transactional
    public UserResponse add(UpdateUserRequest request) {
        User user = repository.getByUserGuid(request.getUserGuid());
        mapper.changeUser(user, request);
        repository.save(user);
        return mapper.toUserResponse(user);
    }
}