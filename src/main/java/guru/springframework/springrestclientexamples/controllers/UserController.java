package guru.springframework.springrestclientexamples.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;

import guru.springframework.springrestclientexamples.service.ApiService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    private final ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping
    public String getUsers(Model model, ServerWebExchange serverWebExchange) {
        model.addAttribute(
            "users",
            apiService.findAllUsers(
                serverWebExchange.getFormData().map(data -> Integer.valueOf(data.getFirst("limit")))
            )
        );

        return "users/index";
    }

}
