package com.epam.training.backend.spring.foundations.domain.repository;

import com.epam.training.backend.spring.foundations.SpringFoundationsApplication;
import com.epam.training.backend.spring.foundations.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setSsn("ABC123");
        employee.setFullName("Adam Smith");
        employee.setPosition("Java SW developer");
        employee.setSalary(2000.0d);
    }

    @Test
    void shouldSaveNewEmployee() {
        // given

        // when
        Employee actual = employeeRepository.save(employee);

        // then
        assertThat(actual.getId()).isPositive();
    }

    @Test
    void shouldFindEmployee() {
        // given
        employee = employeeRepository.save(employee);

        // when
        Optional<Employee> actual = employeeRepository.findById(employee.getId());

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get().getId()).isPositive();
    }

}