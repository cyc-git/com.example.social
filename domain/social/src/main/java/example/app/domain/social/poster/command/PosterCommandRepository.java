package example.app.domain.social.poster.command;

public interface PosterCommandRepository {

    void create(CreatePosterVo createPosterVo);

    boolean isIdOrAccountUsed(long id, String account);
}
