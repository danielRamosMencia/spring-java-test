package com.restapi.sompopo.repositories;

import com.restapi.sompopo.entitites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // For auth
    Optional<UserEntity> findByUsername(String userName);
    boolean existsByUsername(String userName);
}
