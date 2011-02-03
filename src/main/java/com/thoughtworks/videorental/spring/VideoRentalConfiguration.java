package com.thoughtworks.videorental.spring;

import java.util.Arrays;

import com.thoughtworks.videorental.action.*;
import com.thoughtworks.videorental.domain.DetailedMovie;
import com.thoughtworks.videorental.domain.Price;
import com.thoughtworks.videorental.interceptor.AdminRoleInterceptor;
import com.thoughtworks.videorental.util.Feature;
import org.springframework.config.java.annotation.Bean;
import org.springframework.config.java.annotation.Configuration;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.repository.TransactionRepository;
import com.thoughtworks.videorental.interceptor.CustomerLoginInterceptor;
import com.thoughtworks.videorental.repository.SetBasedCustomerRepository;
import com.thoughtworks.videorental.repository.SetBasedMovieRepository;
import com.thoughtworks.videorental.repository.SetBasedRentalRepository;
import com.thoughtworks.videorental.repository.SetBasedTransactionRepository;

@Configuration
public class VideoRentalConfiguration {
	@Bean(scope = "prototype")
	public LoginAction loginAction() {
		return new LoginAction(customerRepository());
	}

	@Bean(scope = "prototype")
	public LogoutAction logoutAction() {
		return new LogoutAction();
	}

	@Bean(scope = "prototype")
	public ViewHomeAction viewHomeAction() {
		return new ViewHomeAction(movieRepository());
	}

	@Bean(scope = "prototype")
	public RentMoviesAction rentMoviesAction() {
		return new RentMoviesAction(movieRepository(), rentalRepository(), transactionRepository());
	}

	@Bean(scope = "prototype")
	public ViewHistoryAction viewHistoryAction() {
		return new ViewHistoryAction(transactionRepository());
	}

	@Bean(scope = "prototype")
	public ViewAdminAction viewAdminAction() {
		return new ViewAdminAction(customerRepository());
	}
	
	@Bean(scope = "prototype")
	public AddCustomerAction addCustomerAction() {
		return new AddCustomerAction(customerRepository());
	}

	@Bean(scope = "prototype")
	public AddMovieAction addMovieAction() {
        if (Feature.DetailedMovies.isEnabled()) {
            return new AddDetailedMovieAction(movieRepository());
        } else {
            return new AddMovieAction(movieRepository());
        }
	}

	@Bean(scope = "prototype")
	public ViewCurrentRentalsAction viewCurrentRentalsAction() {
		return new ViewCurrentRentalsAction(rentalRepository());
	}

	@Bean(scope = "singleton")
	public MovieRepository movieRepository() {
        SetBasedMovieRepository movieRepository = new SetBasedMovieRepository();

        Movie avatar = movie("Avatar", Movie.NEW_RELEASE, "James Cameron", "Sam Worthington", "Zoe Saldana", "Action");
        movieRepository.add(avatar);

        Movie upInTheAir = movie("Up In The Air", Movie.REGULAR, "Jason Reitman", "George Clooney", "Vera Farmiga", "Drama");
        movieRepository.add(upInTheAir);

        Movie findingNemo = movie("Finding Nemo", Movie.CHILDRENS, "Andrew Stanton", "Albert Brooks", "Ellen DeGeneres", "Animation");
        movieRepository.add(findingNemo);

        return movieRepository;
	}

    private Movie movie(String title, Price price, String director, String actor, String actress, String action) {
        if (Feature.DetailedMovies.isEnabled()) {
            DetailedMovie movie = new DetailedMovie(title, price);
            movie.setDirector(director);
            movie.setActor(actor);
            movie.setActress(actress);
            movie.setCategory(action);
            return movie;
        } else {
            return new Movie(title, price);
        }

    }

	@Bean(scope = "singleton")
	public CustomerRepository customerRepository() {
        SetBasedCustomerRepository customerRepository = new SetBasedCustomerRepository();

        if (Feature.AdminAccount.isEnabled()) {
            Customer admin = Customer.createAdminUser("Admin", "admin", "pw");
            customerRepository.add(admin);
        }

        customerRepository.add(new Customer("James Madison", "jmadison", "jm-password"));
        customerRepository.add(new Customer("Zackery Taylor", "ztaylor", "zt-password"));
        customerRepository.add(new Customer("Benjamin Harrison", "bharrison", "bh-password"));
        return customerRepository;
	}

	@Bean(scope = "singleton")
	public RentalRepository rentalRepository() {
		return new SetBasedRentalRepository();
	}

	@Bean(scope = "singleton")
	public TransactionRepository transactionRepository() {
		return new SetBasedTransactionRepository();
	}

	@Bean(scope = "singleton")
	public CustomerLoginInterceptor customerLoginInterceptor() {
		return new CustomerLoginInterceptor();
	}

	@Bean(scope = "singleton")
	public AdminRoleInterceptor adminRoleInterceptor() {
		return new AdminRoleInterceptor();
	}
}
