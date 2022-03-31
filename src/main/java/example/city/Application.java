package example.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("example.city.domain")
@ComponentScan(basePackages = {"example.city.domain.entity"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}