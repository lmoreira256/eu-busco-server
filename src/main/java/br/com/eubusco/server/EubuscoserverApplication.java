package br.com.eubusco.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "br.com.eubusco.server" })
@EnableScheduling
public class EubuscoserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EubuscoserverApplication.class, args);
	}

}
