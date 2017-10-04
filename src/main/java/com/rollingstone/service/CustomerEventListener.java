package com.rollingstone.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventListener implements ApplicationListener<CustomerServiceEvent> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//Customer Change this according to your application needs
	public void onApplicationEvent(CustomerServiceEvent event) {
		CustomerServiceEvent CustomerEvent = (CustomerServiceEvent) event;
		logger.info("Customer " + event.getEventType() + " with details : " + CustomerEvent.getEventCustomer());
	}
}
