package com.the.basic.tech.info.skillstechnologies.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skillstech")
public class SkillsTechnologiesController {
	private static final Logger logger = LoggerFactory.getLogger(SkillsTechnologiesController.class);

	@GetMapping("/getSkills")
	public String getSkills() {
		logger.info("Inside getSkitlls Method.");
		// we can implement DB layer for fetching below details
		return "Java, J2EE, Spring, Spring-Boot, Microservices, Docker, Kubernetes.";
	}
	
	@RequestMapping(value = "/getStudentCourse/{name}")
	public String getStudentDetails(@PathVariable(name = "name") String name) {		
		return "MBA";
	}
}