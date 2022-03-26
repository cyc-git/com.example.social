package example.app.domain.social.poster.favorite.command;

public interface PosterFavoriteCommandRepository {

    void favorite(long posterId, long articleId, long time);

    void unfavorite(long posterId, long articleId);
}
