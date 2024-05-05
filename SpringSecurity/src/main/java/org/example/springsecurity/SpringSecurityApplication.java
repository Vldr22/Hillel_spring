package org.example.springsecurity;

import org.example.springsecurity.dto.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
        //http://localhost:8686

        User myClient = new User("Sara", "sara@gmail.com","qwerty");
        User myClient2 = new User("Jo", "Jo@gmail.com", "123");
        User myClient3 = new User("Vladimir", "vlad@gmail.com","777");
        User myClient4 = new User("Max", "mmm@ukr.net", "12345");


    }

}
