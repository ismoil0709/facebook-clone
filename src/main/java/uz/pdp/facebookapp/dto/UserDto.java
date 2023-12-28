package uz.pdp.facebookapp.dto;

import org.springframework.web.bind.annotation.RequestParam;

public record UserDto(String name, String email, String username, String password) {

}

