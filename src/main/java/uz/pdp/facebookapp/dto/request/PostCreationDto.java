package uz.pdp.facebookapp.dto.request;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public record PostCreationDto(Long userId, @RequestParam(value = "caption",required = false) String caption, MultipartFile media) {
}
