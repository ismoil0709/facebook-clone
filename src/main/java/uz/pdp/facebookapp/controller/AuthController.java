package uz.pdp.facebookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.facebookapp.dto.UserDto;
import uz.pdp.facebookapp.dto.UserLoginDto;
import uz.pdp.facebookapp.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.register(userDto));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
