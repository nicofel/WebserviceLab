package com.example.webservicelab;

import com.example.webservicelab.dtos.DogDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebservicelabApplicationTests {


    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;


    @Test
    void contextLoads() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("http://localhost:8080/dogs/", HttpMethod.GET, new HttpEntity<>(headers), DogDto[].class);

        var result = testClient.getForEntity("http://localhost:"+port+"/dogs/", DogDto[].class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isGreaterThan(0);
    }

}
