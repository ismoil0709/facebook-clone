package uz.pdp.facebookapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.entity.Like;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;
import uz.pdp.facebookapp.repository.LikeRepository;
import uz.pdp.facebookapp.repository.PostRepository;
import uz.pdp.facebookapp.repository.UserRepository;
import uz.pdp.facebookapp.service.LikeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void addLike(Long postId, Long userId) {
        if (postId == null)
            throw new NullOrEmptyException("Post Id");
        if (userId == null)
            throw new NullOrEmptyException("User Id");
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User")
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post")
        );
       post.getLikes().add(Like.builder()
                .post(post)
                .user(user)
                .build());
       postRepository.save(post);
    }

    @Override
    public void cancelLike(Long likeId) {
        if (likeId == null)
            throw new NullOrEmptyException("Like Id");
        Like like = likeRepository.findById(likeId).orElseThrow(
                () -> new NotFoundException("Like")
        );
        Post post = postRepository.findById(like.getPost().getId()).orElseThrow(
                () -> new NotFoundException("Post")
        );
        post.getLikes().remove(like);
        postRepository.save(post);
    }

    @Override
    public List<Like> getAllLikesByPostId(Long postId) {
        if (postId == null)
            throw new NullOrEmptyException("Post Id");
        List<Like> allByPostId = likeRepository.findAllByPostId(postId);
        if (allByPostId.isEmpty())
            throw new NullOrEmptyException("Likes");
        return allByPostId;
    }

    @Override
    public List<Like> getAllLikesByUserId(Long userId) {
        if (userId == null)
            throw new NullOrEmptyException("User Id");
        List<Like> allByUserId = likeRepository.findAllByUserId(userId);
        if (allByUserId.isEmpty())
            throw new NullOrEmptyException("Likes");
        return allByUserId;
    }
}
