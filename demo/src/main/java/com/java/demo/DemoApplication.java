package com.java.demo;

import com.java.demo.entity.Laptop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//@Bean se do IOC tu khoi tao va dua vao Spring Container quan ly
	@Bean(name="laptop1")
	public Laptop laptop1(){
		Laptop l1 = new Laptop();
		l1.setId(1);
		l1.setName("Dell");
		return l1;
	}

	@Bean(name="laptop2")
	public Laptop laptop2(){
		Laptop l1 = new Laptop();
		l1.setId(1);
		l1.setName("Hp");
		return l1;
	}
}


