package guru.springframework.springrestclientexamples.service;

import java.util.List;

import guru.springframework.api.domain.User;

public interface ApiService {

    List<User> findAllUsers(Integer limit);

}
