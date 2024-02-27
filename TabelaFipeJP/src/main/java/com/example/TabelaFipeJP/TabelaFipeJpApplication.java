package com.example.TabelaFipeJP;

import com.example.TabelaFipeJP.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeJpApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeJpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
