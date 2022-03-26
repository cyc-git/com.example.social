package example.app.domain.social.article.star.command;

import example.app.core.i18n.I18nAsserts;
import example.app.domain.social.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Clock;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleStarCommandService {
    private final ArticleService articleService;
    private final ArticleStarCommandRepository articleStarCommandRepository;
    private final Clock clock;

    public void star(
            @NotNull Long articleId,
            @NotNull Long staredBy
    ) {
        I18nAsserts.isTrue(
                articleService.findById(articleId).isPresent(),
                ArticleStarCommandI18nKey.RELATED_ARTICLE_DOES_NOT_EXIST
        );

        articleStarCommandRepository.star(articleId, staredBy, clock.millis());
    }

    public void unstar(
            @NotNull Long articleId,
            @NotNull Long staredBy
    ) {
        articleStarCommandRepository.unstar(articleId, staredBy);
    }
}
