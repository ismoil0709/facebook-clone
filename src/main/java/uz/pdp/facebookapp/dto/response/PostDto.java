package uz.pdp.facebookapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.facebookapp.entity.Like;
import uz.pdp.facebookapp.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String caption;
    private String mediaPath;
    private UserDto createdBy;
    private List<Like> likes;
    private List<CommentDto> comments;
    public PostDto(final Post post){
        this.id = post.getId();
        this.caption = post.getCaption();
        this.mediaPath = "http://localhost:8080/media/post/" + post.getId();
        this.createdBy = new UserDto(post.getCreatedBy().getId(),post.getCreatedBy().getUsername());
        this.likes = post.getLikes();
        if (post.getComments() != null)
            post.getComments().forEach(c->
                this.comments = List.of(new CommentDto(c.getId(),c.getComment()))
            );
    }
    @Getter
    @Setter
    @AllArgsConstructor
    private static class UserDto{
        private Long id;
        private String username;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    private static class CommentDto{
        private Long id;
        private String comment;
    }
}
