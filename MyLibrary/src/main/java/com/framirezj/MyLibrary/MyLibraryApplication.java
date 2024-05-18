package com.framirezj.MyLibrary;

import com.framirezj.MyLibrary.main.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyLibraryApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(MyLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.muestraElMenu();
	}
}
