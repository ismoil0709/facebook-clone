package uz.pdp.facebookapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Column(nullable = false)
    private User createdBy;
    private String caption;
    @Column(nullable = false)
    private byte[] media;
    private Integer likes = 0;
    @OneToMany
    private List<User> likedBy;
    @OneToMany
    private List<Comment> comments;



}
