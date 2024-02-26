package com.green.school.green.repository;

import com.green.school.green.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
   Users findByUsername(String username);
   @Query("SELECT u FROM Users u WHERE u.username=:username")
   Users getUserByUsername(@Param("username") String username);

   @Query("UPDATE Users u SET u.failedAttempt=?1 WHERE u.username = ?2")
   @Modifying
   void updateFailedAttempts(int failAttempts, String username);
}
