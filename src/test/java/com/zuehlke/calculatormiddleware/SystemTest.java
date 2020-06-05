package com.zuehlke.calculatormiddleware;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SystemTest {

    final String baseUriString = "http://localhost:";
    final String summationEndpoint = "/cmw/summation";
    final String multiplicationEndpoint = "/cmw/multiplication";
    final String calculationResultKey = "result";
    final String contentTypeJson = "application/json";

    @LocalServerPort
    int randomServerPort;

    @Test
    public void shouldReturnSumOfNumbers() throws URISyntaxException {
        //Given
        var listOfNumbers = Arrays.asList(15, 23, 31);
        var expectedResult = 69;

        //When
        ResponseEntity<CalculatorResult> result = new RestTemplate().postForEntity(
                new URI(baseUriString + randomServerPort + summationEndpoint),
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
                baseUri(baseUriString + randomServerPort).
                contentType(contentTypeJson).
                when().
                post(multiplicationEndpoint).
                then().assertThat().body(calculationResultKey, equalTo(69));
    }

    @Test
    public void multiplicationResponseTimeShouldBeLessThan500ms() {
        given().
                body(Arrays.asList(1, 3, 23)).
                baseUri(baseUriString + randomServerPort).
                contentType(contentTypeJson).
                when().
                post(multiplicationEndpoint).
                then().time(lessThan(500L));
    }

    @Test
    public void summationResponseTimeShouldBeLessThan500ms() throws URISyntaxException {
        //Given
        var listOfNumbers = Arrays.asList(15, 23, 31);
        var expectedResult = 69;

        //When
        var responseTimer = new StopWatch();
        responseTimer.start();
        ResponseEntity<CalculatorResult> result = new RestTemplate().postForEntity(
                new URI(baseUriString + randomServerPort + summationEndpoint),
                new HttpEntity<>(listOfNumbers),
                CalculatorResult.class);

        //Then
        responseTimer.stop();
        var requestResponseTime = responseTimer.getTotalTimeMillis();
        assertThat(requestResponseTime).isLessThan(500);
    }
}
