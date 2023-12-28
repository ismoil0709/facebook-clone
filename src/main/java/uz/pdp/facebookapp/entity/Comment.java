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
    @ManyToOne
    @Column(nullable = false)
    private User commentedBy;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDate commentedAt;
    @ManyToMany
    private List<Comment> replies;
}
