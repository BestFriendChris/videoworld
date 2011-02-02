package com.thoughtworks.videorental.action;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.util.ParamsUtil;

public class AddCustomerAction extends ActionSupport {

	private final CustomerRepository customerRepository;

    private String displayname = "";
    private String username = "";
    private String password1;
    private String password2;

    private String flashError = "";
    private String adding;

    public AddCustomerAction(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

    public void setAdding(String adding) {
        this.adding = adding;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFlashError() {
        return flashError;
    }

	@Override
	public String execute() throws Exception {
        if (adding == null) { return INPUT; }

        if (ParamsUtil.nullOrEmpty(displayname, username, password1, password2)) {
            flashError = "Must enter all fields";
			return ERROR;
		}

        if (!password1.equals(password2)) {
            flashError = "Passwords must match";
            return ERROR;
        }

        if (customerRepository.containsUsername(username)) {
            flashError = "Customer with username '" + username + "' already exists";
            return ERROR;
        }

        Customer customer = new Customer(displayname, username, password1);
        customerRepository.add(customer);

		return SUCCESS;
	}
}
