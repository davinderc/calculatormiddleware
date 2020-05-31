package com.zuehlke.calculatormiddleware;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorServiceTest {

    CalculatorService calculatorService = new CalculatorService();

    @Test
    void shouldSumNumbers() {
        //Given
        var summands = Arrays.asList(15, 23, 31);

        //When
        var testResult = calculatorService.getSum(summands);

        //Then
        assertThat(testResult).isEqualTo(69);
    }

    @Test
    void shouldMultiplyNumbers() {
        //Given
        var factors = Arrays.asList(1, 3, 23);

        //When
        var testResult = calculatorService.getProduct(factors);

        //Then
        assertThat(testResult).isEqualTo(69);
    }
}