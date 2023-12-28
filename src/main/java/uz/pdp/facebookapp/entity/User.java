package uz.pdp.facebookapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    private byte[] profileImage;
    @Column(nullable = false)
    private String password;
    private String dateOfBirth;
    private String location;
    private String about = "About";
    @OneToMany
    private List<Post> posts;

}
