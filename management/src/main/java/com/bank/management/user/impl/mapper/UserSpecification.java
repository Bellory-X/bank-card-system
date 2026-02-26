package com.bank.management.user.impl.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.bank.management.user.api.UserFilter;
import com.bank.management.user.impl.model.User;
import com.bank.manager.user.api.model.UserRole;

import jakarta.persistence.criteria.Predicate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {

    private UserSpecification() {}

    public static Pageable getPageable(UserFilter filter) {
        return PageRequest.of(
            filter.pageNumber(),
            filter.pageSize(), 
            Sort.by(
                Sort.Direction.fromString(filter.sortDirection().name()),
                filter.sortField().name()
            )
        );
    }

    public static Specification<User> byLogin(String login) {
        return (root, query, cb) -> cb.equal(root.get("login"), login);
    }

    public static Specification<User> byEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<User> byRole(UserRole role) {
        return (root, query, cb) -> cb.equal(root.get("role"), role);
    }

    public static Specification<User> byEnabled(boolean enabled) {
        return (root, query, cb) -> cb.equal(root.get("enabled"), enabled);
    }

    public static Specification<User> byFullNameLike(String name) {
        return (root, query, cb) -> cb.like(
            cb.lower(root.get("fullName")),
            "%" + name.toLowerCase() + "%"
        );
    }

    public static Specification<User> createdAfter(LocalDateTime date) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<User> createdBefore(LocalDateTime date) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<User> lastLoginAfter(LocalDateTime date) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("lastLoginAt"), date);
    }

    public static Specification<User> withFilters(UserFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String pattern = "%" + searchQuery.toLowerCase() + "%";
                Predicate fullNamePredicate = cb.like(cb.lower(root.get("fullName")), pattern);
                Predicate loginPredicate = cb.like(cb.lower(root.get("login")), pattern);
                predicates.add(cb.or(fullNamePredicate, loginPredicate));
            }

            if (role != null) {
                predicates.add(cb.equal(root.get("role"), role));
            }

            if (enabled != null) {
                predicates.add(cb.equal(root.get("enabled"), enabled));
            }

            if (createdFrom != null && createdTo != null) {
                predicates.add(cb.between(root.get("createdAt"), createdFrom, createdTo));
            } else if (createdFrom != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), createdFrom));
            } else if (createdTo != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), createdTo));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}