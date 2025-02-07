package com.restapi.sompopo.entitites;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "msisdn", nullable = false)
    private String msisdn;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public UserEntity(String username, String password, String email, String msisdn, Date createdAt, Date deletedAt,
            Date updatedAt, Long id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.msisdn = msisdn;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.updatedAt = updatedAt;
        this.id = id;
    }
}
