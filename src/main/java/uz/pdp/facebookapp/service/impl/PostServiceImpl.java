package uz.pdp.facebookapp.service.impl;

import uz.pdp.facebookapp.dto.PostDto;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {

    @Override
    public Post save(PostDto post) {
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Post getById(Long id) {
        return null;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public User getCreatedUser(Long userId) {
        return null;
    }

    @Override
    public List<Comment> getComments(Long postId) {
        return null;
    }

    @Override
    public List<User> getLikedUsers(Long postId) {
        return null;
    }
}
