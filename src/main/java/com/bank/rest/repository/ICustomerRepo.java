package com.bank.rest.repository;

import com.bank.rest.data.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepo extends CrudRepository<Customer, Long> {

    Customer findByUsername(String username);

    <S extends Customer> S save(S customer);

}
