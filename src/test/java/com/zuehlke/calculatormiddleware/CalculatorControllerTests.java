package com.zuehlke.calculatormiddleware;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorControllerTests {

    private final CalculatorController calculatorController = new CalculatorController(new MockCalculatorService());

    @Test
    public void calculatorShouldRespondWithList() {
        //Given
        var listOfNumbers = Arrays.asList(15, 23, 31);

        //When
        var response = calculatorController.calculateSum(listOfNumbers);

        //Then
        assertThat(response).isInstanceOf(CalculatorResult.class);
    }

    @Test
    public void responseShouldHaveCalculatedSumAndSummands() {
        //Given
        var listOfNumbers = Arrays.asList(15, 23, 31);

        //When
        var response = calculatorController.calculateSum(listOfNumbers);

        //Then
        assertThat(listOfNumbers).isEqualTo(response.getOperands());
        assertThat(response.getResult()).isEqualTo(10);
    }

    private static class MockCalculatorService extends CalculatorService {
        @Override
        public int getResult(List<Integer> operands) {
            return 10;
        }
    }
}
