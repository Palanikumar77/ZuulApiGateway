package com.the.basic.tech.info.studentinfoservice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.the.basic.tech.info.studentinfoservice.delegate.StudentServiceDelegate;

@RestController
@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
public class StudentServiceApp {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceApp.class);
	@RequestMapping(value = "/echoStudentName/{name}")
	public String echoStudentName(@PathVariable(name = "name") String name) {
		return "Hello  " + name + " Responsed on : " + new Date();
	}

	@Autowired
	StudentServiceDelegate studentServiceDelegate;
	
	@RequestMapping(value = "/getStudentDetails/{name}")
	public Student getStudentDetails(@PathVariable(name = "name") String name) {
		logger.info("Going to call skillstechnologies /getStudentCourse/ service (Hystrix Enabled) to get data!");
		String cls = studentServiceDelegate.callStudentServiceAndGetData(name);
		logger.info("course/class value from skillstechnologies microservice {}", cls);
		return new Student(name, "Northridge, CA", cls, "Block-E", "California State University");
	}	

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApp.class, args);
	}
}

class Student {
	String name;
	String address;
	String cls;
	String university;
	String block;	

	public Student(String name, String address, String cls, String block, String university) {
		super();
		this.name = name;
		this.address = address;
		this.cls = cls;
		this.block = block;
		this.university = university;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCls() {
		return cls;
	}
	public String getUniversity() {
		return university;
	}

	public String getBlock() {
		return block;
	}	

}
