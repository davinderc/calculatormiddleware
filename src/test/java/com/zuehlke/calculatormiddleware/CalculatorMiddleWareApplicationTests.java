package com.zuehlke.calculatormiddleware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CalculatorMiddleWareApplicationTests {

	private HelloWorldController controller = new HelloWorldController(new MockCalculatorService());

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void helloWorldShouldReturnHelloWorld() {
		var name = "sir";
		var template = "Hello, " + name + "!";
		var counter = new AtomicLong();
		var greeting = new Greeting(counter.incrementAndGet(), template);
		assertThat(controller.sayHello(name).getContent()).isEqualTo(greeting.getContent());
	}

	@Test
	public void IdsShouldBeIncremented(){
		var name = "sir";
		var initialId = controller.sayHello(name).getId();
		assertThat(controller.sayHello(name).getId()).isEqualTo(initialId + 1);
		assertThat(controller.sayHello(name).getId()).isEqualTo(initialId + 2);
		assertThat(controller.sayHello(name).getId()).isEqualTo(initialId + 3);
	}

	@Test
	public void calculatorShouldRespondWithHashMap(){
		//Given
		var listOfNumbers = Arrays.asList(15, 23, 31);
		var operands = new HashMap<String, List<Integer>>();
		operands.put("summands", listOfNumbers);

		//When
		var response = controller.calculateSum(operands);

		//Then
		assertThat(response).isInstanceOf(HashMap.class);
	}

	@Test
	public void responseShouldHaveCalculatedSumAndSummands() {
		//Given
		var listOfNumbers = Arrays.asList(15, 23, 31);
		var operands = new HashMap<String, List<Integer>>();
		operands.put("summands", listOfNumbers);

		//When
		var response = controller.calculateSum(operands);

		//Then
		assertThat(listOfNumbers).isIn(response.get("summands"));
		assertThat(response.get("sum")).isEqualTo(10);
	}

	private class MockCalculatorService extends CalculatorService {
		@Override
		public int getResult(List<Integer> operands) {
			return 10;
		}
	}
}
