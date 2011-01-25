package com.thoughtworks.videorental.domain;

import java.util.Set;

public class Customer {
	private final String displayName;

    private final String username;
    private final String password;

	private int frequentRenterPoints = 0;

    public Customer(String displayName, String username, String password) {
        this.displayName = displayName;
        this.username = username;
        this.password = password;
    }

    public String getDisplayName() {
		return displayName;
	}

    public String getUsername() {
        return username;
    }

    public String statement(final Set<Rental> newRentals) {
		String result = "Rental Record for " + getDisplayName() + "\n";

		double totalAmount = 0;
		for (Rental rental : newRentals) {
			// show figures for this rental
			final Integer rentalDays = rental.getPeriod().getDuration().getDays();

			result += "  " + rental.getMovie().getTitle() + "  -  $"
					+ String.valueOf(rental.getMovie().getPrice().getCharge(rentalDays)) + "\n";

			totalAmount += rental.getMovie().getPrice().getCharge(rentalDays);

			frequentRenterPoints += rental.getMovie().getPrice().getFrequentRenterPoints(rentalDays);
		}

		// add footer lines
		result += "Amount charged is $" + String.valueOf(totalAmount) + "\n";
		result += "You have a new total of " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}

    public boolean isUsernameAndPasswordValid(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
