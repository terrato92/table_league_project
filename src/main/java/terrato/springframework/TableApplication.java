package terrato.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class TableApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableApplication.class, args);
	}
}
