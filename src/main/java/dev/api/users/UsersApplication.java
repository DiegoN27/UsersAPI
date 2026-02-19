package dev.api.users;

import dev.api.users.Repository.I_Repo_Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UsersApplication implements CommandLineRunner {

    @Autowired
    private I_Repo_Users repo;

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

    @Override
    public void run(String[] args) throws Exception {
        System.out.println("Aplicacion iniciada correctamente");
    }
}
