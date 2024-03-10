package com.epam.training.backend_services.authdemo.domain.repository;

import com.epam.training.backend_services.authdemo.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
