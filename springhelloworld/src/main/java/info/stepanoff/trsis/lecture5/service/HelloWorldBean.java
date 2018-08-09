package info.stepanoff.trsis.lecture5.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldBean {
    
    String message = "Hello, World!!";
    
    public String getMessage() {
        return message;
    }
}
