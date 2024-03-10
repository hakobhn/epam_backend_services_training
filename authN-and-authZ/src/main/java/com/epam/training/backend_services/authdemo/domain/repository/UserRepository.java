package com.epam.training.backend_services.authdemo.domain.repository;

import com.epam.training.backend_services.authdemo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    @Query("from User u where u.roles LIKE '%ADMIN%'")
    Iterable<User> findAllAdmins();
}
