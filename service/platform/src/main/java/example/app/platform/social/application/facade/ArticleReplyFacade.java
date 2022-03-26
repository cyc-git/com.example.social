package example.app.platform.social.application.facade;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.reply.ArticleReplyService;
import example.app.domain.social.article.reply.IArticleReplyVo;
import example.app.domain.social.article.reply.command.ArticleReplyCommandService;
import example.app.domain.social.article.reply.command.CreateArticleReplyVo;
import example.app.platform.social.application.dto.input.CreateArticleReplyDto;
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
        return convert(articleReplyCommandService.create(vo));
    }

    public List<ArticleReplyDto> findByArticleId(Long articleId) {
        return articleReplyService.findByArticleId(articleId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    ArticleReplyDto convert(IArticleReplyVo replyVo) {
        final var dto = new ArticleReplyDto();
        CachedBeanCopier.copy(replyVo, dto);
        posterFacade.find(replyVo.getRepliedBy()).ifPresent(dto::setRepliedBy);
        return dto;
    }
}
