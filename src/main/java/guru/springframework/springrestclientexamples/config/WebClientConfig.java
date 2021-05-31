package guru.springframework.springrestclientexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://private-anon-0f354b021d-apifaketory.apiary-mock.com")
                      .defaultHeaders(httpHeaders -> {
                          httpHeaders.add(
                              HttpHeaders.HOST,
                              "private-anon-0f354b021d-apifaketory.apiary-mock.com"
                          );
                          httpHeaders.add(HttpHeaders.USER_AGENT, "PostmanRuntime/7.28.0");
                          httpHeaders.add(HttpHeaders.ACCEPT, "*/*");
                          httpHeaders.add(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
                          httpHeaders.add(HttpHeaders.CONNECTION, "keep-alive");
                      })
                      .build();
    }

}
