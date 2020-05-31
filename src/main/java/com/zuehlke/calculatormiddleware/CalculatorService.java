package com.zuehlke.calculatormiddleware;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculatorService {
    public int getResult(List<Integer> operands) {
        var result = 0;
        for (int operand: operands) {
            result += operand;
        }
        return result;
    }
}
