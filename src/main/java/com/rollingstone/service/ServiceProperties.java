package com.rollingstone.service;

import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;


/*
* demonstrates how service-specific properties can be injected
*/
@ConfigurationProperties(prefix = "customer.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {
	
	
	@NotNull // you can also create configurationPropertiesValidator
	private String name = "Customer Service";
	
	//Customer Change Desc
	@NotNull // you can also create configurationPropertiesValidator
	private String description = "Customer Service Description";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
