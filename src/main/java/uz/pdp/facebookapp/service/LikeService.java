package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.entity.Like;

import java.util.List;

@Service
public interface LikeService {
    void addLike(Long postId,Long userId);
    void cancelLike(Long likeId);
    List<Like> getAllLikesByPostId(Long postId);
    List<Like> getAllLikesByUserId(Long userId);
}
