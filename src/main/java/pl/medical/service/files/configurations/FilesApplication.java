package pl.medical.service.files.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication()
@ComponentScan({"pl.medical.service.files", "pl.medical.service.files.repositories", "pl.medical.service.files.models"})
@EnableMongoRepositories("pl.medical.service.files.repositories")

public class FilesApplication {


	public static void main(String[] args) {
		SpringApplication.run(FilesApplication.class, args);
	}

}
