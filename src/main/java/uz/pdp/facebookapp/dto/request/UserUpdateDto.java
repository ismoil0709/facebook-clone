package uz.pdp.facebookapp.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private String location;
    private String about;
}
