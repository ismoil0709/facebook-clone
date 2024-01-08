package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.dto.PostDto;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;

import java.util.List;

@Service
public interface PostService {
    Post save(PostDto post);
    void delete(Long id);
    Post getById(Long id);
    List<Post> getAll();
    List<Post> getByUserId(Long userId);
    List<Comment> getComments(Long postId);
    List<User> getLikedUsers(Long postId);
    void like(Long userId,Long postId);
    void comment(Long userId,Long postId,String comment);
}
