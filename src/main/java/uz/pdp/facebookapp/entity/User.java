package uz.pdp.facebookapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "users")
public class User extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    @ManyToMany
    private List<UserRole> roles;
    private byte[] profileImage;
    @Column(nullable = false)
    private String password;
    private LocalDate dateOfBirth;
    private String location;
    private String about;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Post> posts;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> followers;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> followed;
}
