package com.rollingstone.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.domain.Customer;
import com.rollingstone.exception.HTTP404Exception;
import com.rollingstone.service.CustomerService;
import com.rollingstone.service.CustomerServiceEvent;

@RestController
@RequestMapping(value = "/rsmortgage-Customerservice/v1/Customer")
public class CustomerController extends AbstractRestHandler {

	/*
	 * This is the Public Facing API. Change this to your specific public facing REST API
	 */
	@Autowired
	private CustomerService CustomerService;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public void createCustomer(@RequestBody Customer Customer, HttpServletRequest request, HttpServletResponse response) {
		Customer createdCustomer = this.CustomerService.createCustomer(Customer);
		eventPublisher.publishEvent(new CustomerServiceEvent(this, "CustomerCreated", createdCustomer));
		response.setHeader("Location", request.getRequestURL().append("/").append(createdCustomer.getId()).toString());
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Page<Customer> getAllCustomer(
			@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
			HttpServletRequest request, HttpServletResponse response) {
		return this.CustomerService.getAllCustomers(page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Customer getCustomer(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Customer Customer = this.CustomerService.getCustomer(id);
		checkResourceFound(Customer);
		return Customer;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer Customer, HttpServletRequest request,
			HttpServletResponse response) {
		checkResourceFound(this.CustomerService.getCustomer(id));
		if (id != Customer.getId())
			throw new HTTP404Exception("ID doesn't match!");
		this.CustomerService.updateCustomer(Customer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		checkResourceFound(this.CustomerService.getCustomer(id));
		this.CustomerService.deleteCustomer(id);
	}
}
