package guru.springframework.springrestclientexamples.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder, @Value("${api.host}") String host) {
        return builder.baseUrl("http://" + host).defaultHeaders(httpHeaders -> {
            httpHeaders.add(HttpHeaders.HOST, host);
            httpHeaders.add(HttpHeaders.USER_AGENT, "PostmanRuntime/7.28.0");
            httpHeaders.add(HttpHeaders.ACCEPT, "*/*");
            httpHeaders.add(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
            httpHeaders.add(HttpHeaders.CONNECTION, "keep-alive");
        }).build();
    }

}
