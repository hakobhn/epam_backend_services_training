package com.epam.training.backend_services.authdemo.domain.repository;

import com.epam.training.backend_services.authdemo.domain.model.State;
import com.epam.training.backend_services.authdemo.domain.model.User;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@SqlGroup({
        @Sql(value = "classpath:data/schema.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:data/data.sql", executionPhase = BEFORE_TEST_METHOD)
})
class RepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StateRepository stateRepository;

    @Test
    void testGetAllUsers(){
        Iterable<User> users = userRepository.findAll();
        assert(IterableUtils.size(users)==4);
    }

    @Test
    void testGetAllStates(){
        Iterable<State> state = stateRepository.findAll();
        assert(IterableUtils.size(state)==7);
    }
}
