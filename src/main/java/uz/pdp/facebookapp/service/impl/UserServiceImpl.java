package uz.pdp.facebookapp.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.UserDto;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User register(UserDto user) {
        return null;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<Post> getLikedPosts(Long id) {
        return null;
    }

    @Override
    public List<Post> getComments(Long id) {
        return null;
    }
}
