package uz.pdp.facebookapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.facebookapp.dto.request.PostCreationDto;
import uz.pdp.facebookapp.dto.response.PostDto;

import java.util.List;

@Service
public interface PostService {
    PostDto save(PostCreationDto post);
    void delete(Long id);
    PostDto getById(Long id);
    List<PostDto> getAll();
    List<PostDto> getByUserId(Long userId);
    byte[] getMedia(Long id);
}
