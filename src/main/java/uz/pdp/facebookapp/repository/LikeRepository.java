package uz.pdp.facebookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.facebookapp.entity.Like;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.post.id=?1")
    List<Like> findAllByPostId(Long postId);
    @Query("SELECT l FROM Like l WHERE l.user.id=?1")
    List<Like> findAllByUserId(Long userId);
}
