package guru.springframework.springrestclientexamples.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.api.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApiServiceImpl implements ApiService {

    private final String uri;
    private final String host;

    public ApiServiceImpl(@Value("${api.uri}") String uri, @Value("${api.host}") String host) {
        this.uri = uri;
        this.host = host;
    }

    @Override
    public Flux<User> findAllUsers(Mono<Integer> limit) {
        var uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri)
                                                       .queryParam("_limit", limit.block());

        return WebClient.create("https://" + host)
                        .get()
                        .uri(uriComponentsBuilder.toUriString())
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(User[].class)
                        .flatMapMany(Flux::fromArray);
    }

}
