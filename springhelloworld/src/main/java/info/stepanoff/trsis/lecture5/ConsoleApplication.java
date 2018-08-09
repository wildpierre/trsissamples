package info.stepanoff.trsis.lecture5;

import info.stepanoff.trsis.lecture5.service.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {

    @Autowired
    private HelloWorldBean helloBean;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ConsoleApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(helloBean.getMessage());
        exit(0);
    }
}
