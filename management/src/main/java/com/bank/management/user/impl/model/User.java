package com.bank.management.user.impl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import com.bank.manager.user.api.model.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID guid;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "patronymic", length = 30)
    private String patronymic;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}