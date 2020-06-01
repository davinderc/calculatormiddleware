package com.zuehlke.calculatormiddleware;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SystemTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void shouldReturnSumOfNumbers() throws URISyntaxException {
        //Given
        var listOfNumbers = Arrays.asList(15, 23, 31);
        var expectedResult = 69;

        //When
        ResponseEntity<CalculatorResult> result = new RestTemplate().postForEntity(
                new URI("http://localhost:" + randomServerPort + "/cmw/summation"),
                new HttpEntity<>(listOfNumbers),
                CalculatorResult.class);

        //Then
        var extractedResult = Objects.requireNonNull(result.getBody()).getResult();
        assertThat(extractedResult).isEqualTo(expectedResult);
    }

    @Test
    public void shouldReturnProductOfNumbers() {
        given().
                body(Arrays.asList(1, 3, 23)).
                baseUri("http://localhost:" + randomServerPort).
                contentType("application/json").
                when().
                post("/cmw/multiplication").
                then().assertThat().body("result", equalTo(69));
    }
}
