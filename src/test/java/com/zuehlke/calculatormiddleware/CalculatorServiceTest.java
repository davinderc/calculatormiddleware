package com.zuehlke.calculatormiddleware;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    CalculatorService calculatorService = new CalculatorService();

    @Test
    void shouldSumNumbers() {
        //Given
        var summands = Arrays.asList(15, 23, 31);

        //When
        var testResult = calculatorService.getResult(summands);

        //Then
        assertThat(testResult).isEqualTo(69);
    }
}