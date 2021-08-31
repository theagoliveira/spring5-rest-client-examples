package guru.springframework.springrestclientexamples.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApiServiceImpl implements ApiService {

    private WebClient webClient;
    private final String uri;

    public ApiServiceImpl(WebClient webClient, @Value("${api.uri}") String uri) {
        this.webClient = webClient;
        this.uri = uri;
    }

    @Override
    public Flux<User> findAllUsers(Mono<Integer> limit) {
        var uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri)
                                                       .queryParam("_limit", limit.block());

        return webClient.get()
                        .uri(uriComponentsBuilder.toUriString())
                        .retrieve()
                        .bodyToMono(User[].class)
                        .flatMapMany(Flux::fromArray);
    }

}
