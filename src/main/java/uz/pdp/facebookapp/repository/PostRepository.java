package uz.pdp.facebookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p WHERE p.createdBy.id = ?1")
    List<Post> findByCreatedBy(Long userId);
}
