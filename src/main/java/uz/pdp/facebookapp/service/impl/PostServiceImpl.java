package uz.pdp.facebookapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.PostDto;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;
import uz.pdp.facebookapp.repository.PostRepository;
import uz.pdp.facebookapp.service.PostService;
import uz.pdp.facebookapp.service.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    public Post save(PostDto post) {
        if (post.userId() == null)
            throw new NullOrEmptyException("User Id");
        if (post.media() == null)
            throw new NullOrEmptyException("Media");
        try {
            return postRepository.save(Post.builder()
                    .caption(post.caption())
                    .createdBy(userService.getById(post.userId()))
                    .media(post.media().getInputStream().readAllBytes())
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post")));
    }

    @Override
    public Post getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post"));
    }

    @Override
    public List<Post> getAll() {
        List<Post> all = postRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("Posts");
        return all;
    }

    @Override
    public List<Post> getByUserId(Long userId) {
        if (userId == null)
            throw new NullOrEmptyException("User id");
        List<Post> byCreatedBy = postRepository.getByUser(userId);
        if (byCreatedBy.isEmpty())
            throw new NullOrEmptyException("Posts");
        return byCreatedBy;
    }

    @Override
    public List<Comment> getComments(Long postId) {
        if (postId == null)
            throw new NullOrEmptyException("Post id");
        List<Comment> comments = postRepository.getComments(postId);
        if (comments.isEmpty())
            throw new NullOrEmptyException("Comments");
        return comments;
    }

    @Override
    public List<User> getLikedUsers(Long postId) {
        if (postId == null)
            throw new NullOrEmptyException("Post id");
        List<User> likedUsers = postRepository.getLikedUsers(postId);
        if (likedUsers.isEmpty())
            throw new NullOrEmptyException("Liked users");
        return likedUsers;

    }

    @Override
    public void like(Long userId, Long postId) {
        if (postId == null)
            throw new NotFoundException("Post");
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post"));
        post.getLikedBy().add(userService.getById(userId));
        postRepository.save(post);
    }

    @Override
    public void comment(Long userId, Long postId, String comment) {
        if (postId == null)
            throw new NotFoundException("Post");
        if (comment == null || comment.isBlank() || comment.isEmpty())
            throw new NullOrEmptyException("Comment");

        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post"));
        post.getComments().add(Comment.builder()
                .comment(comment)
                .commentedAt(LocalDate.now())
                .commentedBy(userService.getById(userId))
                .build());
        postRepository.save(post);
    }
}
