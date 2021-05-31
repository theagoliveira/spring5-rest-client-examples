package guru.springframework.springrestclientexamples.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.api.domain.User;

@SpringBootTest
class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @Test
    void testFindAllUsers() {
        List<User> users = apiService.findAllUsers(1);

        assertEquals(1, users.size());
    }

}
