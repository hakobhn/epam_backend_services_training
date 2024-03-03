package com.epam.training.backend_services.rest.service.impl.sql.domain.repository;


import com.epam.training.backend_services.rest.service.impl.sql.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
