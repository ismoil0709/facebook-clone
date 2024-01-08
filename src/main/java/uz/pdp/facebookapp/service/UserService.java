package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.dto.UserDto;
import uz.pdp.facebookapp.dto.UserLoginDto;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;

import java.util.List;

@Service
public interface UserService {
    User register(UserDto user);
    User login(UserLoginDto loginDto);
    User update(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
    User getByUsername(String username);
    User getByEmail(String email);
    List<Post> getLikedPosts(Long id);
    List<Post> getComments(Long id);
    List<User> getFollowers(Long id);
    List<User> getFollowed(Long id);
    void addImageToProfile(Long id,MultipartFile multipartFile);
    void follow(Long userId,Long followedUserId);

}
