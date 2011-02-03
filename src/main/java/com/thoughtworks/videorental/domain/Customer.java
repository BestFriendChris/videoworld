package com.thoughtworks.videorental.domain;

import com.thoughtworks.videorental.util.Feature;

import java.util.Set;

public class Customer {
	private final String displayName;

    private final String username;
    private final String password;

    private final boolean isAdmin;

	private int frequentRenterPoints = 0;

    public static Customer createAdminUser(String displayName, String username, String password) {
        if (!Feature.AdminAccount.isEnabled()) {
            throw new RuntimeException("Admin account feature is not enabled");
        }
        return new Customer(displayName, username, password, true);
    }

    public Customer(String displayName, String username, String password) {
        this(displayName, username, password, false);
    }

    private Customer(String displayName, String username, String password, boolean isAdmin) {
        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getDisplayName() {
		return displayName;
	}

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
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
