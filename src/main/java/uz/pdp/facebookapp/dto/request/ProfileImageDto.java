package uz.pdp.facebookapp.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record ProfileImageDto(Long id, MultipartFile file) {

}
