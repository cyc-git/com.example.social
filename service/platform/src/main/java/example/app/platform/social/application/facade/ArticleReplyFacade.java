package example.app.platform.social.application.facade;

import example.app.domain.social.article.reply.ArticleReplyService;
import example.app.domain.social.article.reply.command.ArticleReplyCommandService;
import example.app.domain.social.article.reply.command.CreateArticleReplyVo;
import example.app.platform.social.application.dto.input.CreateArticleReplyDto;
import example.app.platform.social.application.dto.output.ArticleReplyAggregateDto;
import example.app.platform.social.application.dto.output.ArticleReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleReplyFacade {
    private final ArticleReplyService articleReplyService;
    private final ArticleReplyCommandService articleReplyCommandService;
    private final PosterFacade posterFacade;

    public ArticleReplyDto reply(
            @NotNull @Valid CreateArticleReplyDto createArticleReplyDto,
            Long posterId
    ) {
        final var vo = new CreateArticleReplyVo();
        vo.setContent(createArticleReplyDto.getContent());
        vo.setArticleId(createArticleReplyDto.getArticleId());
        vo.setRepliedBy(posterId);
        return new ArticleReplyDto(articleReplyCommandService.create(vo));
    }

    public ArticleReplyAggregateDto findByArticleId(Long articleId) {
        final var result = new ArticleReplyAggregateDto();
        final var replies = articleReplyService.findByArticleId(articleId)
                .stream()
                .map(ArticleReplyDto::new)
                .collect(Collectors.toList());
        result.setReplies(replies);

        final var posterIds = replies.stream()
                .map(ArticleReplyDto::getRepliedBy)
                .collect(Collectors.toSet());
        if (posterIds.isEmpty()) {
            result.setPosters(List.of());
        } else {
            result.setPosters(posterFacade.findByIds(posterIds));
        }

        return result;
    }
}
