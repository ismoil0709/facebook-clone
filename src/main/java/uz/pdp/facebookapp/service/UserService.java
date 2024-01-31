package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.JwtDto;
import uz.pdp.facebookapp.dto.request.ProfileImageDto;
import uz.pdp.facebookapp.dto.request.UserRegisterDto;
import uz.pdp.facebookapp.dto.request.UserLoginDto;
import uz.pdp.facebookapp.dto.request.UserUpdateDto;
import uz.pdp.facebookapp.dto.response.UserDto;

import java.util.List;

@Service
public interface UserService {
    JwtDto register(UserRegisterDto registerDto);
    JwtDto login(UserLoginDto loginDto);
    JwtDto update(UserUpdateDto updateDto);
    void delete(Long id);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto getByUsername(String username);
    UserDto getByEmail(String email);
    List<UserDto> getFollowers(Long id);
    List<UserDto> getFollowed(Long id);
    void addImageToProfile(ProfileImageDto imageDto);
    void follow(Long userId,Long followedUserId);
    byte[] getProfileImage(Long id);
}
