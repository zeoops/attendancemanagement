package com.finalproject.bcs.attendancemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = "com.finalproject.bcs.attendancemanagement")

public class AttendancemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendancemanagementApplication.class, args);
	}

}
