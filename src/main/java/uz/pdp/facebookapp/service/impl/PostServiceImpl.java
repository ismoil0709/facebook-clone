package uz.pdp.facebookapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.request.PostCreationDto;
import uz.pdp.facebookapp.dto.response.PostDto;
import uz.pdp.facebookapp.entity.Comment;
import uz.pdp.facebookapp.entity.Post;
import uz.pdp.facebookapp.entity.User;
import uz.pdp.facebookapp.exception.NotFoundException;
import uz.pdp.facebookapp.exception.NullOrEmptyException;
import uz.pdp.facebookapp.repository.PostRepository;
import uz.pdp.facebookapp.repository.UserRepository;
import uz.pdp.facebookapp.service.PostService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostDto save(PostCreationDto post) {
        if (post.userId() == null)
            throw new NullOrEmptyException("User Id");
        if (post.caption() == null || post.caption().isBlank() || post.caption().isEmpty())
            throw new NullOrEmptyException("Caption");
        if (post.media() == null)
            throw new NullOrEmptyException("Media");
        User user = userRepository.findById(post.userId()).orElseThrow(() -> new NotFoundException("User"));
        try {
            return new PostDto(postRepository.save(Post.builder()
                    .caption(post.caption())
                    .createdBy(user)
                    .media(post.media().getInputStream().readAllBytes())
                    .build()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post")));
    }

    @Override
    public PostDto getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new PostDto(postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post")));
    }

    @Override
    public List<PostDto> getAll() {
        List<Post> all = postRepository.findAll();
        if (all.isEmpty())
            throw new NullOrEmptyException("Posts");
        return all.stream().map(PostDto::new).toList();
    }

    @Override
    public List<PostDto> getByUserId(Long userId) {
        if (userId == null)
            throw new NullOrEmptyException("User id");
        List<Post> byCreatedBy = postRepository.findByCreatedBy(userId);
        if (byCreatedBy.isEmpty())
            throw new NullOrEmptyException("Posts");
        return byCreatedBy.stream().map(PostDto::new).toList();
    }

    @Override
    public byte[] getMedia(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Post")
        ).getMedia();
    }
}
