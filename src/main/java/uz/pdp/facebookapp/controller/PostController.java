package uz.pdp.facebookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.facebookapp.dto.request.PostCreationDto;
import uz.pdp.facebookapp.dto.response.PostDto;
import uz.pdp.facebookapp.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @PostMapping(value = "/save")
    public ResponseEntity<PostDto> save(@RequestBody PostCreationDto postCreationDto){
        return ResponseEntity.ok(postService.save(postCreationDto));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }
    @GetMapping("/all/own/{userId}")
    public ResponseEntity<List<PostDto>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getByUserId(userId));
    }
}