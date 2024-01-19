package br.com.dbc.vemser.notifica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "config")
public class NotificaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificaApplication.class, args);
	}

}
