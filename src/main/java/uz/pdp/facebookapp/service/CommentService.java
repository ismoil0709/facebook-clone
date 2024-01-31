package uz.pdp.facebookapp.service;


import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.request.CommentCreationDto;
import uz.pdp.facebookapp.dto.response.CommentDto;
import uz.pdp.facebookapp.entity.Comment;

import java.util.List;

@Service
public interface CommentService {
    void addComment(CommentCreationDto commentDto);
    void  deleteComment(Long commentId);
    void replyComment(CommentCreationDto commentDto);
    void deleteReply(Long commentId,Long replyId);
    List<CommentDto> getCommentsByPostId(Long postId);
    List<CommentDto> getCommentsByUserId(Long userId);
}