package com.zuehlke.calculatormiddleware;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculatorService {
    public int getSum(List<Integer> operands) {
        var result = 0;
        for (int operand : operands) {
            result += operand;
        }
        return result;
    }

    public int getProduct(List<Integer> operands) {
        var result = 1;
        for (int operand : operands) {
            result *= operand;
        }
        return result;
    }
}
