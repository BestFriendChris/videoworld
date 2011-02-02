package com.thoughtworks.videorental.domain.specification;

import com.thoughtworks.ddd.specification.Specification;
import com.thoughtworks.videorental.domain.Customer;
import org.hibernate.Criteria;

public class CustomerWithUsernameSpecification implements Specification<Customer> {
    private final String username;

    public CustomerWithUsernameSpecification(String username) {
        this.username = username;
    }

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return username.equals(customer.getUsername());
    }

    @Override
    public void populateCriteria(Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }
}
