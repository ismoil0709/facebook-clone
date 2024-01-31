package uz.pdp.facebookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.facebookapp.entity.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.commentedBy.id = ?1")
    List<Comment> findAllByUserId(Long userId);
    @Query("SELECT c FROM Comment c WHERE c.post.id=?1")
    List<Comment> findAllByPostId(Long postId);
}
