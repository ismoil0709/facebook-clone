package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.UserDto;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;

import java.util.List;

@Service
public interface UserService {
    User register(UserDto user);
    User login(String username,String password);
    User update(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
    User getByUsername(String username);
    User getByEmail(String email);
    List<Post> getLikedPosts(Long id);
    List<Post> getComments(Long id);
}
