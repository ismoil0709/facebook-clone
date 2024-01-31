package uz.pdp.facebookapp.dto.request;

public record CommentCreationDto(Long postId,Long userId,String comment) {
}
