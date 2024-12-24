package com.greatestsasha.training.service;

import com.greatestsasha.training.model.Customer;
import com.greatestsasha.training.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Flux<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Mono<ResponseEntity<Customer>> getById(Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<Void> saveAll(Publisher<Customer> customerFlux) {
        return customerRepository.saveAll(customerFlux).then();
    }
}