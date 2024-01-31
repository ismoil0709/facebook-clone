package uz.pdp.facebookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.facebookapp.dto.JwtDto;
import uz.pdp.facebookapp.dto.request.UserRegisterDto;
import uz.pdp.facebookapp.dto.request.UserLoginDto;
import uz.pdp.facebookapp.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<JwtDto> register(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.register(userRegisterDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody UserLoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
