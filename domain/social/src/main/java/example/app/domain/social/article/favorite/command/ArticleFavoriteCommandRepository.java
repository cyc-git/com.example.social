package example.app.domain.social.article.favorite.command;

public interface ArticleFavoriteCommandRepository {

    void favorite(long articleId, long favoritedBy, long time);


    void unfavorite(long articleId, long favoritedBy);
}
