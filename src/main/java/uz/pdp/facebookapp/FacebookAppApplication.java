package uz.pdp.facebookapp;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.facebookapp.repository.UserRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class FacebookAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(FacebookAppApplication.class, args);
    }

}
