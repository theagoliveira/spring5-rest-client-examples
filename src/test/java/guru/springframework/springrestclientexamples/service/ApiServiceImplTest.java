package guru.springframework.springrestclientexamples.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.api.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @Test
    void testFindAllUsers() {
        Flux<User> users = apiService.findAllUsers(Mono.just(1));

        assertEquals(1, users.count().block());
    }

}
