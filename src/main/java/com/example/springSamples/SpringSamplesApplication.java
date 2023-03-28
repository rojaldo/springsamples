package com.example.springSamples;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.springSamples.entities.BeerEntity;
import com.example.springSamples.entities.BookEntity;
import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.BeersRepository;
import com.example.springSamples.repositories.BookRepository;
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
	public CommandLineRunner addCustomers(CustomerRepository customerRepository, BookRepository bookRepository) {
		return (args) -> {
			customerRepository.save(new CustomerEntity("John", "Doe", "John@algo.com", 20));
			customerRepository.save(new CustomerEntity("Jane", "Doe", "Jane@algo.com", 30));
			bookRepository.save(new BookEntity("The Lord of the Rings", "J.R.R. Tolkien", "234567890"));
			bookRepository.save(new BookEntity("Romeo and Juliet", "William Shakespeare", "34567890"));
			bookRepository.save(new BookEntity("The Silmarillion", "J.R.R. Tolkien", "4567890"));
			bookRepository.save(new BookEntity("The Poems of William Blake", "William Blake", "567890"));
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
/*
	@Bean
	public CommandLineRunner addCountries(CountriesRepository countryRepository, CountriesService countriesService) {
		return (args) -> {
			for (CountryEntity b : countriesService.getCountriesFromApi()) {
				countryRepository.save(b);
			}

		};
	}
 */
}
