package example.app.domain.social.article.reply.command;

import example.app.common.core.i18n.I18nAsserts;
import example.app.domain.social.article.ArticleService;
import example.app.domain.social.article.reply.IArticleReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Clock;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleReplyCommandService {
    private final ArticleService articleService;
    private final ArticleReplyCommandRepository articleReplyCommandRepository;
    private final Clock clock;

    public IArticleReplyVo create(@NotNull @Valid CreateArticleReplyVo createArticleReplyVo) {
        I18nAsserts.isTrue(
                articleService.findById(createArticleReplyVo.getArticleId()).isPresent(),
                ArticleReplyCommandI18nKey.RELATED_ARTICLE_DOES_NOT_EXIST
        );

        return articleReplyCommandRepository.reply(createArticleReplyVo, clock.millis());
    }
}
