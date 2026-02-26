package com.bank.management.user.impl.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bank.management.user.exception.NotFoundUserException;
import com.bank.management.user.impl.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    public default User getByUserGuid(UUID userGuid) {
        return findById(userGuid).orElseThrow(() -> new NotFoundUserException());
    }
}