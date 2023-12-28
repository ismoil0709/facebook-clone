package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.PostDto;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;

import java.util.List;

@Service
public interface PostService {
    Post save(PostDto post);
    Post update(Post post);
    void delete(Long id);
    Post getById(Long id);
    List<Post> getAll();
    User getCreatedUser(Long userId);
    List<Comment> getComments(Long postId);
    List<User> getLikedUsers(Long postId);
}
