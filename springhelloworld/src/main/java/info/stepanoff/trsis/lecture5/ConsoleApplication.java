package info.stepanoff.trsis.lecture5;

import info.stepanoff.trsis.lecture5.service.HelloWorldBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ConsoleApplication implements CommandLineRunner {

    private final HelloWorldBean helloBean;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ConsoleApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(helloBean.getMessage());
        exit(0);
    }
}
