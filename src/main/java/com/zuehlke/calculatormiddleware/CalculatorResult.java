package com.zuehlke.calculatormiddleware;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CalculatorResult {
    private List<Integer> operands;
    private Integer result;

    @JsonCreator
    public CalculatorResult(@JsonProperty("operands") List<Integer> operands,
                            @JsonProperty("result") Integer result) {
        this.operands = operands;
        this.result = result;
    }


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
