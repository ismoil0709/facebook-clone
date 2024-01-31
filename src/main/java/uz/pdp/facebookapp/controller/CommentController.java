package uz.pdp.facebookapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.facebookapp.dto.request.CommentCreationDto;
import uz.pdp.facebookapp.dto.response.CommentDto;
import uz.pdp.facebookapp.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/")
    public void addComment(@RequestBody CommentCreationDto commentCreationDto){
        commentService.addComment(commentCreationDto);
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getCommentsByPostId(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsByUserId(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getCommentsByUserId(id));
    }
    @PostMapping("/reply")
    public void reply(@RequestBody CommentCreationDto commentCreationDto){
        commentService.replyComment(commentCreationDto);
    }
    @DeleteMapping("/reply/{commentId}/{id}")
    public void deleteReply(@PathVariable Long commentId,@PathVariable Long id){
        commentService.deleteReply(commentId,id);
    }
    @GetMapping("/post/count/{postId}")
    public ResponseEntity<Integer> getCommentCountByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId).size());
    }
    @GetMapping ("/user/count/{userId}")
    public ResponseEntity<Integer> getCommentCountByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId).size());
    }
}
