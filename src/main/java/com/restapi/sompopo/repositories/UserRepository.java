package com.restapi.sompopo.repositories;

import com.restapi.sompopo.entitites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.restapi.sompopo.dtos.AllUsersDto;
import java.util.List;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // For auth
    Optional<UserEntity> findByUsername(String userName);
    boolean existsByUsername(String userName);

    // CRUD Methods
    @Query("SELECT u FROM UserEntity u WHERE u.deletedAt IS NULL")
    List<UserEntity> findAllActiveUsers();
}
