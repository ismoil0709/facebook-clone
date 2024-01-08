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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "commented_by_id")
    private User commentedBy;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDate commentedAt;
    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> replies;
}
