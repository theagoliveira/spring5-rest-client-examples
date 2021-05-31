package guru.springframework.springrestclientexamples;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;

import reactor.core.publisher.Mono;

class WebClientExamples {

    public static final String API_ROOT = "https://api.predic8.de:443/shop";
    public static final WebClient webClient = WebClient.builder().baseUrl(API_ROOT).build();

    @Test
    void getCategories() throws Exception {
        String apiUrl = "/categories/";

        JsonNode jsonNode = webClient.get()
                                     .uri(apiUrl)
                                     .retrieve()
                                     .bodyToMono(JsonNode.class)
                                     .share()
                                     .block();

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    void getCustomers() throws Exception {
        String apiUrl = "/customers/";

        JsonNode jsonNode = webClient.get()
                                     .uri(apiUrl)
                                     .retrieve()
                                     .bodyToMono(JsonNode.class)
                                     .share()
                                     .block();

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    void createCustomer() throws Exception {
        String apiUrl = "/customers/";

        // Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "Joe");
        postMap.put("lastname", "Buck");

        JsonNode jsonNode = webClient.post()
                                     .uri(apiUrl)
                                     .body(Mono.just(postMap), Map.class)
                                     .retrieve()
                                     .bodyToMono(JsonNode.class)
                                     .share()
                                     .block();

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    void updateCustomer() throws Exception {

        // create customer to update
        String apiUrl = "/customers/";

        // Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "Micheal");
        postMap.put("lastname", "Weston");

        JsonNode jsonNode = webClient.post()
                                     .uri(apiUrl)
                                     .body(Mono.just(postMap), Map.class)
                                     .retrieve()
                                     .bodyToMono(JsonNode.class)
                                     .share()
                                     .block();

        System.out.println("Response");
        System.out.println(jsonNode.toString());

        String customerUrl = jsonNode.get("customer_url").textValue();

        String id = customerUrl.split("/")[3];

        System.out.println("Created customer id: " + id);

        postMap.put("firstname", "Micheal 2");
        postMap.put("lastname", "Weston 2");

        webClient.put()
                 .uri(apiUrl + id)
                 .body(Mono.just(postMap), Map.class)
                 .retrieve()
                 .bodyToMono(JsonNode.class)
                 .share()
                 .block();

        JsonNode updatedNode = webClient.get()
                                        .uri(apiUrl + id)
                                        .retrieve()
                                        .bodyToMono(JsonNode.class)
                                        .share()
                                        .block();

        System.out.println(updatedNode.toString());

    }

    @Test
    void updateCustomerUsingPatchSunHttp() throws Exception {

        // create customer to update
        String apiUrl = "/customers/";

        // Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "Sam");
        postMap.put("lastname", "Axe");

        JsonNode jsonNode = webClient.post()
                                     .uri(apiUrl)
                                     .body(Mono.just(postMap), Map.class)
                                     .retrieve()
                                     .bodyToMono(JsonNode.class)
                                     .share()
                                     .block();

        System.out.println("Response");
        System.out.println(jsonNode.toString());

        String customerUrl = jsonNode.get("customer_url").textValue();

        String id = customerUrl.split("/")[3];

        System.out.println("Created customer id: " + id);

        postMap.put("firstname", "Sam 2");
        postMap.put("lastname", "Axe 2");

        JsonNode updatedNode = webClient.patch().uri(apiUrl + id).headers(h -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
        })
                                        .body(Mono.just(postMap), Map.class)
                                        .retrieve()
                                        .bodyToMono(JsonNode.class)
                                        .share()
                                        .block();

        System.out.println(updatedNode.toString());

    }

    @Test
    void deleteCustomer() throws Exception {

        // create customer to update
        String apiUrl = "/customers/";

        // Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "Les");
        postMap.put("lastname", "Claypool");

        JsonNode jsonNode = webClient.post()
                                     .uri(apiUrl)
                                     .body(Mono.just(postMap), Map.class)
                                     .retrieve()
                                     .bodyToMono(JsonNode.class)
                                     .share()
                                     .block();

        System.out.println("Response");
        System.out.println(jsonNode.toString());

        String customerUrl = jsonNode.get("customer_url").textValue();

        String id = customerUrl.split("/")[3];

        System.out.println("Created customer id: " + id);

        webClient.delete().uri(apiUrl + id).retrieve().bodyToMono(JsonNode.class).share().block();

        System.out.println("Customer deleted");

        Mono<JsonNode> customer = webClient.get()
                                           .uri(apiUrl + id)
                                           .retrieve()
                                           .bodyToMono(JsonNode.class);

        // should go boom on 404
        assertThrows(NotFound.class, () -> {
            customer.block();
        });
    }

}
