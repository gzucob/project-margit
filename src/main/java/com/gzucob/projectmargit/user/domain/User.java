package com.gzucob.projectmargit.user.domain;

import com.gzucob.projectmargit.user.dto.UserRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_first_name", nullable = false, length = 255)
    private String userFirstName;

    @Column(name = "user_last_name", nullable = false, length = 255)
    private String userLastName;

    @Column(name = "user_full_name", nullable = false, length = 510)
    private String userFullName;

    @Column(name = "user_email", nullable = false, unique = true, length = 254)
    private String userEmail;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @CreationTimestamp
    @Column(name = "user_created_at", nullable = false, updatable = false)
    private Instant userCreatedAt;

    public User(UserRequest userRequest) {
        this.userFirstName = userRequest.userFirstName();
        this.userLastName = userRequest.userLastName();
        this.userEmail = userRequest.userEmail();
        this.passwordHash = userRequest.passwordHash();
    }

    @PrePersist
    @PreUpdate
    public void prepareFullName() {
        if (this.userFirstName != null && this.userLastName != null) {
            this.userFullName = this.userFirstName + " " + this.userLastName;
        }
    }
}
