package example.app.domain.social.poster.follow.command;

public interface PosterFollowCommandRepository {

    void follow(long posterId, long followedBy, long time);

    void unfollow(long posterId, long followedBy);
}
