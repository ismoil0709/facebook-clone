package uz.pdp.facebookapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import uz.pdp.facebookapp.entity.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String username;
    private List<UserRoleDto> roles;
    private LocalDate dateOfBirth;
    private String location;
    private String about;
    private String imagePath;

    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.roles = user.getRoles().stream().map(r->new UserRoleDto(r.getId(),r.getName())).toList();
        this.dateOfBirth = user.getDateOfBirth();
        this.location = user.getLocation();
        this.about = user.getAbout();
        this.imagePath = "http://localhost:8080/media/user/" + user.getId();
    }
    @Getter
    @Setter
    @AllArgsConstructor
    private static class UserRoleDto implements Serializable {
        private Long id;
        private String name;
    }
}