package com.zuehlke.calculatormiddleware;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CalculatorMiddleWareApplicationTests {

	private final HelloWorldController greetingController = new HelloWorldController();

	@Test
	public void contextLoads() {
		assertThat(greetingController).isNotNull();
	}

	@Test
	public void helloWorldShouldReturnHelloWorld() {
		var name = "sir";
		var template = "Hello, " + name + "!";
		var counter = new AtomicLong();
		var greeting = new Greeting(counter.incrementAndGet(), template);
		assertThat(greetingController.sayHello(name).getContent()).isEqualTo(greeting.getContent());
	}

	@Test
	public void IdsShouldBeIncremented() {
		var name = "sir";
		var initialId = greetingController.sayHello(name).getId();
		assertThat(greetingController.sayHello(name).getId()).isEqualTo(initialId + 1);
		assertThat(greetingController.sayHello(name).getId()).isEqualTo(initialId + 2);
		assertThat(greetingController.sayHello(name).getId()).isEqualTo(initialId + 3);
	}


}
