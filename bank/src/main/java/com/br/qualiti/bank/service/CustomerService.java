package com.br.qualiti.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.qualiti.bank.exception.ResourceNotFoundException;
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

	public List<Customer> findAll() {
		return customerRepository.findAll();

	}

	public Optional<Customer> findById(long id) {
		return customerRepository.findById(id);
	}

	public Customer update(long id, Customer customer) {
		Optional<Customer> currentCustomer = customerRepository.findById(id);
		if (currentCustomer.isPresent()) {
			currentCustomer.get().setName(customer.getName());
			currentCustomer.get().setCPF(customer.getCPF());
			currentCustomer.get().getAccounts().addAll(customer.getAccounts());
			return customerRepository.save(currentCustomer.get());
		} else {
			throw new ResourceNotFoundException("Customer", "Client", "O cliente com id:" + id + " nao foi encontrado");
		}
	}
    
	public void delete(long id) {
		try {
			customerRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {

		}
	}
}
