package com.zuehlke.calculatormiddleware;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/cmw/summation")
    @ResponseBody
    public CalculatorResult calculateSum(@RequestBody List<Integer> operands) {
        var result = calculatorService.getResult(operands);
        return new CalculatorResult(operands, result);
    }
}
