package uz.pdp.facebookapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.dto.JwtDto;
import uz.pdp.facebookapp.dto.request.ProfileImageDto;
import uz.pdp.facebookapp.dto.request.UserRegisterDto;
import uz.pdp.facebookapp.dto.request.UserLoginDto;
import uz.pdp.facebookapp.dto.request.UserUpdateDto;
import uz.pdp.facebookapp.dto.response.UserDto;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.entity.UserRole;
import uz.pdp.facebookapp.exception.AlreadyExistsException;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;
import uz.pdp.facebookapp.repository.UserRepository;
import uz.pdp.facebookapp.security.jwt.JwtTokenProvider;
import uz.pdp.facebookapp.service.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtDto register(UserRegisterDto user) {
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

        return new JwtDto(jwtTokenProvider.generateToken(
                userRepository.save(
                        User.builder()
                                .name(user.name())
                                .email(user.email())
                                .username(user.username())
                                .roles(List.of(new UserRole(1L,"ROLE_USER")))
                                .password(passwordEncoder.encode(user.password()))
                                .about("About")
                                .location("Location")
                                .dateOfBirth(LocalDate.now())
                                .build())
        ));
    }

    @Override
    public JwtDto login(UserLoginDto userLoginDto) {
        if (userLoginDto.username() == null || userLoginDto.username().isEmpty() || userLoginDto.username().isBlank())
            throw new NullOrEmptyException("Username");
        if (userLoginDto.password() == null || userLoginDto.password().isEmpty() || userLoginDto.password().isBlank())
            throw new NullOrEmptyException("Username");
        User user = userRepository.findByUsername(userLoginDto.username()).orElseThrow(
                () -> new NotFoundException("User"));
        if (user.getPassword().equals(userLoginDto.password()))
            return new JwtDto(jwtTokenProvider.generateToken(user));
        else throw new NotFoundException("User");
    }

    @Override
    public JwtDto update(UserUpdateDto updateDto) {
        User userById = userRepository.findById(updateDto.getId()).orElseThrow(() -> new NotFoundException("User"));

        if (updateDto.getUsername() != null && userRepository.findByUsername(updateDto.getUsername()).isPresent())
            throw new AlreadyExistsException("Username");
        if (updateDto.getEmail() != null && userRepository.findByEmail(updateDto.getEmail()).isPresent())
            throw new AlreadyExistsException("Email");


        return new JwtDto(jwtTokenProvider.generateToken(userRepository.save(
                User.builder()
                        .id(updateDto.getId())
                        .name(Objects.requireNonNullElse(updateDto.getName(), userById.getName()))
                        .email(Objects.requireNonNullElse(updateDto.getEmail(), userById.getEmail()))
                        .username(Objects.requireNonNullElse(updateDto.getUsername(), userById.getUsername()))
                        .password(Objects.requireNonNullElse(updateDto.getPassword(), userById.getPassword()))
                        .dateOfBirth(Objects.requireNonNullElse(updateDto.getDateOfBirth(), userById.getDateOfBirth()))
                        .location(Objects.requireNonNullElse(updateDto.getLocation(), userById.getLocation()))
                        .about(Objects.requireNonNullElse(updateDto.getAbout(), userById.getAbout()))
                        .build()
        )));
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User")));
    }

    @Override
    public UserDto getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new UserDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User")));
    }

    @Override
    public List<UserDto> getAll() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("Users");
        return all.stream().map(UserDto::new).toList();
    }

    @Override
    public UserDto getByUsername(String username) {
        if (username == null || username.isEmpty() || username.isBlank())
            throw new NullOrEmptyException("Username");
        return new UserDto(userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User")));
    }

    @Override
    public UserDto getByEmail(String email) {
        if (email == null || email.isEmpty() || email.isBlank())
            throw new NullOrEmptyException("Email");
        return new UserDto(userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User")));
    }
    @Override
    public List<UserDto> getFollowers(Long id) {
        if (id == null)
            throw new NotFoundException("User");
        List<User> followers = userRepository.getFollowers(id);
        if (followers.isEmpty())
            throw new NullOrEmptyException("Followers");
        return followers.stream().map(UserDto::new).toList();
    }

    @Override
    public List<UserDto> getFollowed(Long id) {
        if (id == null)
            throw new NullOrEmptyException("User");
        List<User> followed = userRepository.getFollowed(id);
        if (followed.isEmpty())
            throw new NullOrEmptyException("Followed");
        return followed.stream().map(UserDto::new).toList();
    }

    @Override
    public void addImageToProfile(ProfileImageDto imageDto) {
        if (imageDto.file() == null)
            throw new NullOrEmptyException("Image");
        if (imageDto.id() == null)
            throw new NullOrEmptyException("Id");

        User user = userRepository.findById(imageDto.id()).orElseThrow(() -> new NotFoundException("User"));
        try {
            user.setProfileImage(imageDto.file().getInputStream().readAllBytes());
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
        userRepository.saveAll(List.of(user, followedUser));
    }

    @Override
    public byte[] getProfileImage(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()->new NotFoundException("User")
        ).getProfileImage();
    }

}
