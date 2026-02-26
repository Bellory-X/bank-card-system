package com.bank.management.user.api;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.bank.manager.card.api.model.SortDirection;
import com.bank.manager.user.api.model.UserRole;
import com.bank.manager.user.api.model.UserSortField;

public record UserFilter(
    String login,
    String surname,
    String name,
    String patronymic,
    String email,
    String phone,
    UserRole role,
    Boolean enabled,
    ZonedDateTime createdAt,
    Integer pageNumber,
    Integer pageSize,
    UserSortField sortField,
    SortDirection sortDirection
) {
    public UserFilter {
        if (Objects.isNull(pageNumber)) {
            pageNumber = 0;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 10;
        }
        if (Objects.nonNull(sortField) && Objects.isNull(sortDirection)) {
            sortDirection = SortDirection.ASC;
        }
    }
}
