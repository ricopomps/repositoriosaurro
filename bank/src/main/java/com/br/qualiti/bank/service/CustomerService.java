package com.br.qualiti.bank.service;

import org.springframework.stereotype.Service;

import com.br.qualiti.bank.model.Customer;
import com.br.qualiti.bank.repository.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;

	CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
}
