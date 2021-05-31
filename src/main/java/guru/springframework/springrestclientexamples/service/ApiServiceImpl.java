package guru.springframework.springrestclientexamples.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> findAllUsers(Integer limit) {
        var userData = restTemplate.getForObject(
            "http://private-anon-0f354b021d-apifaketory.apiary-mock.com/api/user?limit=" + limit,
            UserData.class
        );

        return userData.getData();
    }

}
