package com.epam.training.backend_services.rest.service.impl.sql.domain.repository;

import com.epam.training.backend_services.rest.service.impl.sql.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
