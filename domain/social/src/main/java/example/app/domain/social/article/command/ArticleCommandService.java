package example.app.domain.social.article.command;

import example.app.common.core.i18n.I18nAsserts;
import example.app.domain.social.article.ArticleService;
import example.app.domain.social.article.IArticleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Clock;
import java.util.Optional;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleCommandService {
    private final ArticleService articleService;
    private final ArticleCommandRepository articleCommandRepository;
    private final Clock clock;

    public IArticleVo create(@NotNull @Valid CreateArticleVo createArticleVo) {
        final var hasSharingArticle = createArticleVo.getSharingArticleId() != null;
        final var sharingArticle =
                hasSharingArticle ?
                        articleService.findById(createArticleVo.getSharingArticleId()) :
                        Optional.<IArticleVo>empty();

        I18nAsserts.isFalse(
                hasSharingArticle && sharingArticle.isEmpty(),
                ArticleCommandI18nKey.SHARING_ARTICLE_DOES_NOT_EXIST
        );

        return articleCommandRepository.post(createArticleVo, clock.millis());
    }
}
