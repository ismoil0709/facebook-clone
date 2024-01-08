package uz.pdp.facebookapp.dto;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.facebookapp.entity.User;

public record PostDto(Long userId, @RequestParam(value = "caption",required = false) String caption,MultipartFile media) {
}
