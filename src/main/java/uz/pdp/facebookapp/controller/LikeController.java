package uz.pdp.facebookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.facebookapp.entity.Like;
import uz.pdp.facebookapp.service.LikeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;
    @GetMapping("/{postId}/{userId}")
    public void addLike(@PathVariable Long postId,@PathVariable Long userId){
        likeService.addLike(postId,userId);
    }
    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId){
        likeService.cancelLike(likeId);
    }
    @GetMapping("/post/{id}")
    public ResponseEntity<List<Like>> getLikesByPostId(@PathVariable Long id){
        return ResponseEntity.ok(likeService.getAllLikesByPostId(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Like>> getLikesByUserId(@PathVariable Long id){
        return ResponseEntity.ok(likeService.getAllLikesByUserId(id));
    }
    @GetMapping("/post/count/{postId}")
    public ResponseEntity<Integer> countLikesByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(likeService.getAllLikesByPostId(postId).size());
    }
    @GetMapping("/user/count/{userId}")
    public ResponseEntity<Integer> countLikesByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(likeService.getAllLikesByUserId(userId).size());
    }
}
