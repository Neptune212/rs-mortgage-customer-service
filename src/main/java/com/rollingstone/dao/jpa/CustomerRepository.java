package com.rollingstone.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.domain.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	// Customer Change this JPA Repository to add custom query methods  to suit your application
	
	Page<Customer> findAll(Pageable pageable);
}
