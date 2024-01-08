package uz.pdp.facebookapp.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.dto.PostDto;
import uz.pdp.facebookapp.service.PostService;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @PostMapping(value = "/save")
    public ResponseEntity<?> save(PostDto postDto){
        return ResponseEntity.ok(postService.save(postDto));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getById(id));
    }
    @GetMapping("/like/{userId}/{postId}")
    public void like(@PathVariable Long postId, @PathVariable Long userId){
        postService.like(userId,postId);
    }
    @GetMapping("/comment/{userId}/{postId}/{comment}")
    public void comment(@PathVariable Long userId,@PathVariable Long postId,@PathVariable String comment){
        postService.comment(userId,postId,comment);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }
    @GetMapping("/all/comments/{postId}")
    public ResponseEntity<?> getComments(@PathVariable Long postId){
       return ResponseEntity.ok(postService.getComments(postId));
    }
    @GetMapping("/all/likes/{postId}")
    public ResponseEntity<?> getLikes(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getLikedUsers(postId));
    }
    @GetMapping("/all/own/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getByUserId(userId));
    }
}