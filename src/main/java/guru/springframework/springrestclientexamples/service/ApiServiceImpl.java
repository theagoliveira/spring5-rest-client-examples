package guru.springframework.springrestclientexamples.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;

@Service
public class ApiServiceImpl implements ApiService {

    private WebClient webClient;
    private final String uri;

    public ApiServiceImpl(WebClient webClient, @Value("${api.uri}") String uri) {
        this.webClient = webClient;
        this.uri = uri;
    }

    @Override
    public List<User> findAllUsers(Integer limit) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri)
                                                                        .queryParam("limit", limit);

        var userData = webClient.get()
                                .uri(uriComponentsBuilder.toUriString())
                                .retrieve()
                                .bodyToMono(UserData.class);

        return userData.map(UserData::getData).block();
    }

}
