package com.greatestsasha.training.repository;

import com.greatestsasha.training.model.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends R2dbcRepository<Customer, Long> {

    @Query("SELECT * FROM customer")
    Flux<Customer> getAllUsers();

    @Query("INSERT INTO customer(id, first_name, last_name) " +
            "VALUES (:#{#customer.id}, :#{#customer.first_name}, :#{#customer.last_name})")
    Mono<Void> insert(@Param("user") Customer customer);
}