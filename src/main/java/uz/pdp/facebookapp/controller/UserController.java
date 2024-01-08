package uz.pdp.facebookapp.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.service.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }
    @GetMapping("/all/comments/{postId}")
        public ResponseEntity<?> getComments(@PathVariable Long postId){
        return ResponseEntity.ok(userService.getComments(postId));
    }
    @GetMapping("/all/likes/{postId}")
    public ResponseEntity<?> getLikes(@PathVariable Long postId){
        return ResponseEntity.ok(userService.getLikedPosts(postId));
    }
    @GetMapping("/all/followers/{userId}")
    public ResponseEntity<?> getFollowers(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getFollowers(userId));
    }
    @GetMapping("/all/followed/{userId}")
    public ResponseEntity<?> getFollowed(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getFollowed(userId));
    }
    @PostMapping("/addImage")
    public void addImage(Long id, MultipartFile img) {
        userService.addImageToProfile(id, img);
    }
    @GetMapping("/follow/{userId}/{followedUserId}")
    public void follow(@PathVariable Long userId,@PathVariable Long followedUserId){
        userService.follow(userId,followedUserId);
    }
}
