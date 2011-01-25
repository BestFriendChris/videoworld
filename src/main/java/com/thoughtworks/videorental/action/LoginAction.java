package com.thoughtworks.videorental.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.specification.CustomerWithUsernameAndPasswordSpecification;
import com.thoughtworks.videorental.domain.specification.CustomersOrderedByNameComparator;

public class LoginAction extends ActionSupport {

	private final CustomerRepository customerRepository;

    private String username;
    private String password;

	private Customer loggedInCustomer;

	public LoginAction(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Customer> getCustomers() {
		return customerRepository.selectAll(new CustomersOrderedByNameComparator());
	}

	public Customer getLoggedInCustomer() {
		return loggedInCustomer;
	}

	@Override
	public String execute() throws Exception {
		if (username == null) {
			return LOGIN;
		}

		loggedInCustomer = customerRepository.selectUnique(new CustomerWithUsernameAndPasswordSpecification(username, password));
		if (loggedInCustomer == null) {
			return LOGIN;
		}

		return SUCCESS;
	}
}
