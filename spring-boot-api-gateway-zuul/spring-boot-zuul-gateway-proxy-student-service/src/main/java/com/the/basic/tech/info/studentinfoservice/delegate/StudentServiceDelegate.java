package com.the.basic.tech.info.studentinfoservice.delegate;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class StudentServiceDelegate {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceDelegate.class);
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	public String callStudentServiceAndGetData(String name) {
		logger.info("Getting StudentSubject details for {}", name);
		String response = restTemplate
				.exchange("http://localhost:8989/portal/skillTechService/skillstech/getStudentCourse/{name}",
						HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
						}, name)
				.getBody();

		logger.info("Response Received as " + response + " -  " + new Date());

		return "NORMAL FLOW. Student Course: " + response;
	}

	@SuppressWarnings("unused")
	private String callStudentServiceAndGetData_Fallback(String name) {
		logger.info("Skills Tech Service is down!!! Hystrix fallback route enabled for this service.");
		return "CIRCUIT BREAKER ENABLED. No Response From Skills Tech Service at this moment. Service will be back Shortly.";
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
