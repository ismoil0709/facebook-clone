package uz.pdp.facebookapp.dto.response;


import jdk.jfr.Timestamp;
import lombok.*;
import uz.pdp.facebookapp.entity.Comment;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommentDto {
    private Long postId;
    private Long userId;
    private String comment;
    public CommentDto(Comment comment){
        this.postId = comment.getPost().getId();
        this.userId = comment.getCommentedBy().getId();
        this.comment = comment.getComment();
    }
}
