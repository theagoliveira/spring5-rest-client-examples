package guru.springframework.springrestclientexamples.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;

@Service
public class ApiServiceImpl implements ApiService {

    private WebClient webClient;

    public ApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<User> findAllUsers(Integer limit) {
        var userData = webClient.get()
                                .uri("/api/user?limit=" + limit)
                                .retrieve()
                                .bodyToMono(UserData.class);

        return userData.map(UserData::getData).block();
    }

}
