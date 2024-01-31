package uz.pdp.facebookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.service.PostService;
import uz.pdp.facebookapp.service.UserService;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/media")
public class MediaController {
    private final PostService postService;
    private final UserService userService;
    @GetMapping("post/{id}")
    public ResponseEntity<byte[]> post(@PathVariable Long id){
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(postService.getMedia(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<byte[]> profile(@PathVariable Long id){
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(userService.getProfileImage(id));
    }
}
