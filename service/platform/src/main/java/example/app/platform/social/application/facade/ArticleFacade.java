package example.app.platform.social.application.facade;

import example.app.domain.social.article.ArticleService;
import example.app.domain.social.article.command.ArticleCommandService;
import example.app.domain.social.article.command.CreateArticleVo;
import example.app.platform.social.application.dto.input.CreateArticleDto;
import example.app.platform.social.application.dto.output.ArticleAggregateDto;
import example.app.platform.social.application.dto.output.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleFacade {
    private final ArticleService articleService;
    private final ArticleCommandService articleCommandService;
    private final PosterFacade posterFacade;

    public ArticleDto create(CreateArticleDto createArticleDto, @NotNull Long posterId) {
        final var vo = new CreateArticleVo();
        vo.setContent(createArticleDto.getContent());
        vo.setSharingArticleId(createArticleDto.getSharingArticleId());
        vo.setPosterId(posterId);
        return new ArticleDto(articleCommandService.create(vo));
    }

    public Optional<ArticleAggregateDto> findAggregate(Long id) {
        return articleService.findById(id)
                .map(vo -> {
                    final var aggregate = new ArticleAggregateDto();
                    aggregate.setArticle(new ArticleDto(vo));
                    final var posterIds = new HashSet<Long>();
                    posterIds.add(vo.getPosterId());
                    if (vo.getSharingArticleId() != null) {
                        final var sharingArticle = articleService.findById(vo.getSharingArticleId());
                        sharingArticle.ifPresent(a -> {
                            aggregate.setSharingArticle(new ArticleDto(a));
                            posterIds.add(a.getPosterId());
                        });
                    }
                    aggregate.setPosters(posterFacade.findByIds(posterIds));
                    return aggregate;
                });
    }

    public List<ArticleDto> findByIds(Set<Long> ids) {
        return articleService.findByIds(ids)
                .stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }
}
