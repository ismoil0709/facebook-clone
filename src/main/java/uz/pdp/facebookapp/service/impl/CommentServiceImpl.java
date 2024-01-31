package uz.pdp.facebookapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.request.CommentCreationDto;
import uz.pdp.facebookapp.dto.response.CommentDto;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;
import uz.pdp.facebookapp.repository.CommentRepository;
import uz.pdp.facebookapp.repository.PostRepository;
import uz.pdp.facebookapp.repository.UserRepository;
import uz.pdp.facebookapp.service.CommentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Override
    public void addComment(CommentCreationDto commentDto) {
        if (commentDto.userId() == null)
            throw new NullOrEmptyException("User Id");
        if (commentDto.postId() == null) {
            throw new NullOrEmptyException("Post Id");
        }
        if (commentDto.comment() == null || commentDto.comment().isBlank() || commentDto.comment().isEmpty())
            throw new NullOrEmptyException("Comment");
        User user = userRepository.findById(commentDto.userId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        Post post = postRepository.findById(commentDto.postId()).orElseThrow(
                () -> new NotFoundException("Post")
        );
        post.getComments().add(commentRepository.save(Comment.builder()
                .comment(commentDto.comment())
                .commentedBy(user)
                .commentedAt(LocalDate.now())
                .post(post)
                .build()));
        postRepository.save(post);
    }

    @Override
    public void deleteComment(Long commentId) {
        if (commentId == null)
            throw new NullOrEmptyException("Comment Id");
        commentRepository.deleteById(commentId);
    }

    @Override
    public void replyComment(CommentCreationDto commentDto) {
        if (commentDto.postId() == null)
            throw new NullOrEmptyException("Comment Id");
        if (commentDto.userId() == null)
            throw new NullOrEmptyException("User Id");
        if (commentDto.comment() == null || commentDto.comment().isBlank() || commentDto.comment().isEmpty())
            throw new NullOrEmptyException("Comment");
        User user = userRepository.findById(commentDto.userId()).orElseThrow(
                () -> new NotFoundException("User")
        );
        Comment comment1 = commentRepository.findById(commentDto.postId()).orElseThrow(
                () -> new NotFoundException("Comment")
        );
        comment1.getReplies().add(Comment.builder()
                .comment(commentDto.comment())
                .commentedBy(user)
                .commentedAt(LocalDate.now())
                .build()
        );
        commentRepository.save(comment1);
    }

    @Override
    public void deleteReply(Long commentId,Long replyId) {
        if (commentId == null)
            throw new NullOrEmptyException("Comment Id");
        if (replyId == null)
            throw new NullOrEmptyException("Reply Id");
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment")
        );
        comment.getReplies().forEach(r->{
            if (Objects.equals(r.getId(), replyId))
                comment.getReplies().remove(r);
            commentRepository.save(comment);
        });
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        if (postId == null)
            throw new NullOrEmptyException("Post Id");
        List<Comment> allByPostId = commentRepository.findAllByPostId(postId);
        if (allByPostId.isEmpty())
            throw new NullOrEmptyException("Comments");
        return allByPostId.stream().map(CommentDto::new).toList();
    }

    @Override
    public List<CommentDto> getCommentsByUserId(Long userId) {
        if (userId == null)
            throw new NullOrEmptyException("User Id");
        List<Comment> allByUserId = commentRepository.findAllByUserId(userId);
        if (allByUserId.isEmpty())
            throw new NullOrEmptyException("Comments");
        return allByUserId.stream().map(CommentDto::new).toList();
    }
}
