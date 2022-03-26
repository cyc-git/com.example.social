package example.app.domain.social.article.star.command;

public interface ArticleStarCommandRepository {

    void star(long articleId, long staredBy, long time);

    void unstar(long articleId, long staredBy);
}
