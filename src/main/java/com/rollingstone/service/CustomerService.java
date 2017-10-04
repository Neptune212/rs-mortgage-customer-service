package com.rollingstone.service;

import com.rollingstone.dao.jpa.CustomerRepository;
import com.rollingstone.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepository CustomerRepository;
	
	//Customer Change this according to your Application Domain Service i.e. PersonService , CustomerService, ProductService etc. 
	@Autowired
	CounterService counterService;
	

	public CustomerService() {
	}

	public Customer createCustomer(Customer Customer) {
		return CustomerRepository.save(Customer);
	}

	public Customer getCustomer(long id) {
		return CustomerRepository.findOne(id);
	}

	public void updateCustomer(Customer Customer) {
		CustomerRepository.save(Customer);
	}

	public void deleteCustomer(Long id) {
		CustomerRepository.delete(id);
	}

	public Page<Customer> getAllCustomers(Integer page, Integer size) {
		Page<Customer> pageOfCustomers = CustomerRepository.findAll(new PageRequest(page, size));
		// example of adding to the /metrics
		if (size > 50) {
			log.info("Large Page Size for getAllCustomers");
			counterService.increment("com.rollingstone.CustomerService.getAll.largePayload");
		}
		return pageOfCustomers;
	}
}
