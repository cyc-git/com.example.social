package example.app.domain.social.poster.follow;

import lombok.Data;

@Data
public class PosterFollowVo implements IPosterFollowVo {
    private Long posterId;
    private Long followedBy;
    private Long followedAt;
}
