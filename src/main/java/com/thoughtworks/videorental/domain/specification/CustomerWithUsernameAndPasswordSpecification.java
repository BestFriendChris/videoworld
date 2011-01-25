package com.thoughtworks.videorental.domain.specification;

import com.thoughtworks.ddd.specification.Specification;
import com.thoughtworks.videorental.domain.Customer;
import org.hibernate.Criteria;

public class CustomerWithUsernameAndPasswordSpecification implements Specification<Customer> {
    private final String username;
    private final String password;

    public CustomerWithUsernameAndPasswordSpecification(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return customer.isUsernameAndPasswordValid(username, password);
    }

    @Override
    public void populateCriteria(Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }
}
