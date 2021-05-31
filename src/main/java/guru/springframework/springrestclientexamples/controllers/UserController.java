package guru.springframework.springrestclientexamples.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
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
        log.info("Inside getUsers");

        MultiValueMap<String, String> map = serverWebExchange.getFormData().share().block();

        var limit = Integer.valueOf(map.get("limit").get(0));

        log.info("Received limit value: " + limit);

        if (limit == null || limit == 0) {
            log.info("Setting limit to default of 10");
            limit = 10;
        }

        model.addAttribute("users", apiService.findAllUsers(limit));

        return "users/index";
    }

}
