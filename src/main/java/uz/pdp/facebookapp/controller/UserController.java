package uz.pdp.facebookapp.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.dto.JwtDto;
import uz.pdp.facebookapp.dto.request.ProfileImageDto;
import uz.pdp.facebookapp.dto.request.UserUpdateDto;
import uz.pdp.facebookapp.dto.response.UserDto;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.repository.UserRepository;
import uz.pdp.facebookapp.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PatchMapping("/update")
    public ResponseEntity<JwtDto> update(@RequestBody UserUpdateDto user) {
        return ResponseEntity.ok(userService.update(user));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }
    @GetMapping("/all/followers/{userId}")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getFollowers(userId));
    }
    @GetMapping("/all/followed/{userId}")
    public ResponseEntity<List<UserDto>> getFollowed(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getFollowed(userId));
    }
    @PostMapping("/addImage")
    public void addImage(ProfileImageDto imageDto) {
        userService.addImageToProfile(imageDto);
    }
    @GetMapping("/follow/{userId}/{followedUserId}")
    public void follow(@PathVariable Long userId,@PathVariable Long followedUserId){
        userService.follow(userId,followedUserId);
    }
}
