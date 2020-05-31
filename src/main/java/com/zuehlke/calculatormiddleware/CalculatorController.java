package com.zuehlke.calculatormiddleware;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/cmw/summation")
    @ResponseBody
    public CalculatorResult calculateSum(@RequestBody List<Integer> operands) {
        var result = calculatorService.getSum(operands);
        return new CalculatorResult(operands, result);
    }

    @PostMapping("/cmw/multiplication")
    @ResponseBody
    public CalculatorResult calculateProduct(@RequestBody List<Integer> operands) {
        var result = calculatorService.getProduct(operands);
        return new CalculatorResult(operands, result);
    }
}
