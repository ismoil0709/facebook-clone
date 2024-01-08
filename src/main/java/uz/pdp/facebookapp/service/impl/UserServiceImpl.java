package uz.pdp.facebookapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.dto.UserDto;
import uz.pdp.facebookapp.dto.UserLoginDto;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.exception.AlreadyExistsException;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;
import uz.pdp.facebookapp.repository.UserRepository;
import uz.pdp.facebookapp.service.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User register(UserDto user) {
        if (user.name() == null || user.name().isEmpty() || user.name().isBlank())
            throw new NullOrEmptyException("Name");
        if (user.username() == null || user.username().isEmpty() || user.username().isBlank())
            throw new NullOrEmptyException("Username");
        if (user.email() == null || user.email().isEmpty() || user.email().isBlank())
            throw new NullOrEmptyException("Email");
        if (user.password() == null || user.password().isEmpty() || user.password().isBlank())
            throw new NullOrEmptyException("Password");
        if (userRepository.findByUsername(user.username()).isPresent())
            throw new AlreadyExistsException("Username");
        if (userRepository.findByEmail(user.email()).isPresent())
            throw new AlreadyExistsException("Email");

        return userRepository.save(User.builder()
                .name(user.name())
                .email(user.email())
                .username(user.username())
                .password(user.password())
                .about("About")
                .location("Location")
                .dateOfBirth(LocalDate.now())
                .build());
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        if (userLoginDto.username() == null || userLoginDto.username().isEmpty() || userLoginDto.username().isBlank())
            throw new NullOrEmptyException("Username");
        if (userLoginDto.password() == null || userLoginDto.password().isEmpty() || userLoginDto.password().isBlank())
            throw new NullOrEmptyException("Username");
        User user = userRepository.findByUsername(userLoginDto.username()).orElseThrow(
                () -> new NotFoundException("User"));
        if (user.getPassword().equals(userLoginDto.password()))
            return user;
        else throw new NotFoundException("User");
    }

    @Override
    public User update(User user) {
        if (user == null || user.getId() == null)
            throw new NotFoundException("User");
        User userById = userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User"));

        if (user.getUsername() != null && userRepository.findByUsername(user.getUsername()).isPresent())
            throw new AlreadyExistsException("Username");
        if (user.getEmail() != null && userRepository.findByEmail(user.getEmail()).isPresent())
            throw new AlreadyExistsException("Email");


        return userRepository.save(
                User.builder()
                        .id(user.getId())
                        .name(Objects.requireNonNullElse(user.getName(), userById.getName()))
                        .email(Objects.requireNonNullElse(user.getEmail(), userById.getEmail()))
                        .username(Objects.requireNonNullElse(user.getUsername(), userById.getUsername()))
                        .password(Objects.requireNonNullElse(user.getPassword(), userById.getPassword()))
                        .dateOfBirth(Objects.requireNonNullElse(user.getDateOfBirth(), userById.getDateOfBirth()))
                        .location(Objects.requireNonNullElse(user.getLocation(), userById.getLocation()))
                        .about(Objects.requireNonNullElse(user.getAbout(), userById.getAbout()))
                        .build()
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User")));
    }

    @Override
    public User getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User"));
    }

    @Override
    public List<User> getAll() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("Users");
        return all;
    }

    @Override
    public User getByUsername(String username) {
        if (username == null || username.isEmpty() || username.isBlank())
            throw new NullOrEmptyException("Username");
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User"));
    }

    @Override
    public User getByEmail(String email) {
        if (email == null || email.isEmpty() || email.isBlank())
            throw new NullOrEmptyException("Email");
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User"));
    }

    @Override
    public List<Post> getLikedPosts(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");

        List<Post> likedPosts = userRepository.getLikedPosts(id);
        if (likedPosts.isEmpty())
            throw new NullOrEmptyException("Liked posts");
        return likedPosts;
    }

    @Override
    public List<Post> getComments(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");

        List<Post> comments = userRepository.getComments(id);
        if (comments.isEmpty())
            throw new NullOrEmptyException("Comments");
        return comments;
    }

    @Override
    public List<User> getFollowers(Long id) {
        if (id == null)
            throw new NotFoundException("User");
        List<User> followers = userRepository.getFollowers(id);
        if (followers.isEmpty())
            throw new NullOrEmptyException("Followers");
        return followers;
    }

    @Override
    public List<User> getFollowed(Long id) {
        if (id == null)
            throw new NullOrEmptyException("User");
        List<User> followed = userRepository.getFollowed(id);
        if (followed.isEmpty())
            throw new NullOrEmptyException("Followed");
        return followed;
    }

    @Override
    public void addImageToProfile(Long id, MultipartFile multipartFile) {
        if (multipartFile == null)
            throw new NullOrEmptyException("Image");
        if (id == null)
            throw new NullOrEmptyException("Id");

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User"));
        try {
            user.setProfileImage(multipartFile.getInputStream().readAllBytes());
            userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void follow(Long userId, Long followedUserId) {
        if (userId == null || followedUserId == null)
            throw new NotFoundException("User");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User"));
        User followedUser = userRepository.findById(followedUserId)
                .orElseThrow(() -> new NotFoundException("User"));
        user.getFollowers().add(followedUser);
        followedUser.getFollowed().add(user);
        userRepository.saveAll(List.of(user,followedUser));
    }

}
