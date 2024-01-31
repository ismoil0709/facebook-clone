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
public class Post extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by_id",nullable = false)
    private User createdBy;
    private String caption;
    @Column(nullable = false)
    private byte[] media;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Like> likes;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> comments;



}
