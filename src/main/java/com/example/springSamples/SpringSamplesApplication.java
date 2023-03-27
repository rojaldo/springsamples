package com.example.springSamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.springSamples.entities.BeerEntity;
import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.BeersRepository;
import com.example.springSamples.repositories.CustomerRepository;
import com.example.springSamples.services.BeersService;

@SpringBootApplication
public class SpringSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSamplesApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner addCustomers(CustomerRepository customerRepository) {
		return (args) -> {
			customerRepository.save(new CustomerEntity("John", "Doe", "John@algo.com", 20));
			customerRepository.save(new CustomerEntity("Jane", "Doe", "Jane@algo.com", 30));

		};
	}

	@Bean
	public CommandLineRunner addBeers(BeersRepository beerRepository, BeersService beersService) {
		return (args) -> {
			for (BeerEntity b : beersService.getBeersFromApi()) {
				beerRepository.save(b);
			}

		};
	}

}
