package com.epam.training.backend.spring.foundations.domain.repository;


import com.epam.training.backend.spring.foundations.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("delete from Employee e where e.ssn=:ssn")
    void deleteBySsn(@Param("ssn") String ssn);
}
