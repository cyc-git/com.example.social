package example.app.domain.social.poster.follow;

import javax.validation.constraints.NotNull;

public interface IPosterFollowVo {

    @NotNull
    Long getPosterId();

    @NotNull
    Long getFollowedBy();

    @NotNull
    Long getFollowedAt();
}
