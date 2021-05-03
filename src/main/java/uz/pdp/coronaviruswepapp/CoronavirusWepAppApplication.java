package uz.pdp.coronaviruswepapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusWepAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusWepAppApplication.class, args);
	}

}
