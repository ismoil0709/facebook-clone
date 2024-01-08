package uz.pdp.facebookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query("SELECT p FROM Post p JOIN p.likedBy u WHERE u.id=?1")
    List<Post> getLikedPosts(Long userId);
    @Query("SELECT p FROM Post p JOIN p.likedBy u WHERE u.id=?1")
    List<Post> getComments(Long userId);
    @Query("SELECT u.followers FROM User u WHERE u.id=?1")
    List<User> getFollowers(Long userId);
    @Query("SELECT u.followed FROM User u WHERE u.id=?1")
    List<User> getFollowed(Long userId);
}
