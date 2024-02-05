package br.com.dbc.vemser.notifica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificaApplication.class, args);
	}

}
