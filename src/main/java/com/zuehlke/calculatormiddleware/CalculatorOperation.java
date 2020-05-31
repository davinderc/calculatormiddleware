package com.zuehlke.calculatormiddleware;

import java.util.List;

public class CalculatorOperation {
    private List<Integer> operands;
    private Integer result;


    public List<Integer> getOperands() {
        return operands;
    }

    public void setOperands(List<Integer> operands) {
        this.operands = operands;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
