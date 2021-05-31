package guru.springframework.springrestclientexamples.service;

import guru.springframework.api.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiService {

    Flux<User> findAllUsers(Mono<Integer> limit);

}
