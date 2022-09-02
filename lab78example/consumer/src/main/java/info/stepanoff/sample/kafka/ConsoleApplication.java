package info.stepanoff.sample.kafka;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ConsoleApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
