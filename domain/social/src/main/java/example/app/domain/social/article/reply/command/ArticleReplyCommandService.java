package example.app.domain.social.article.reply.command;

import example.app.core.i18n.I18nAsserts;
import example.app.domain.social.article.ArticleCacheName;
import example.app.domain.social.article.ArticleService;
import example.app.domain.social.article.reply.ArticleReplyCacheName;
import example.app.domain.social.article.reply.IArticleReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
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

    @Caching(
            evict = {
                    @CacheEvict(
                            cacheNames = ArticleReplyCacheName.MULTIPLE_BY_ARTICLE_ID,
                            key = "#result.articleId"
                    ),
                    @CacheEvict(
                            cacheNames = ArticleCacheName.SINGLE_BY_ID,
                            key = "#result.articleId"
                    )
            }
    )
    public IArticleReplyVo create(@NotNull @Valid CreateArticleReplyVo createArticleReplyVo) {
        I18nAsserts.isTrue(
                articleService.findById(createArticleReplyVo.getArticleId()).isPresent(),
                ArticleReplyCommandI18nKey.RELATED_ARTICLE_DOES_NOT_EXIST
        );

        return articleReplyCommandRepository.reply(createArticleReplyVo, clock.millis());
    }
}
