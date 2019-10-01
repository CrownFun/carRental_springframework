package pl.filewicz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.filewicz.application.ApplicationController;

import java.util.Scanner;

@SpringBootApplication
public class CarRental2019SpringApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(CarRental2019SpringApplication.class, args);
		ApplicationController app = ctx.getBean(ApplicationController.class);
		app.mainLoop();


	}
	@Bean
	public Scanner getScanner(){
		return new Scanner(System.in);
	}

}
