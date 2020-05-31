package com.zuehlke.calculatormiddleware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final CalculatorService calculatorService;

    public HelloWorldController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="sir") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/hello-world")
    @ResponseBody
    public Greeting mirrorMirror(@RequestBody Map<String, String> name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name.get("name")));
    }

    @PostMapping("/cmw/summation")
    @ResponseBody
    public Map<String, Object> calculateSum(@RequestBody Map <String, List<Integer>> operands) {
        var result = calculatorService.getResult(operands.get("summands"));
        var response = new HashMap<String, Object>();
        response.put("summands", operands.get("summands"));
        response.put("sum", result);
        return response;
    }

}