package guru.springframework.springrestclientexamples.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import guru.springframework.springrestclientexamples.service.ApiService;

@WebFluxTest(controllers = UserController.class, excludeAutoConfiguration = {
        ReactiveSecurityAutoConfiguration.class})
class UserControllerTest {

    @Autowired
    ApplicationContext applicationContext;

    @MockBean
    ApiService apiService;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    void testGetUsers() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("limit", "3");

        webTestClient.post()
                     .uri("/users")
                     .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                     .body(BodyInserters.fromFormData(formData))
                     .exchange()
                     .expectStatus()
                     .isOk();
    }

}
