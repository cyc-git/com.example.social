package example.app.platform.social.application.facade;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.ArticleService;
import example.app.domain.social.article.IArticleVo;
import example.app.domain.social.article.command.ArticleCommandService;
import example.app.domain.social.article.command.CreateArticleVo;
import example.app.platform.social.application.dto.input.CreateArticleDto;
import example.app.platform.social.application.dto.output.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
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
        final var dto = convert(articleCommandService.create(vo));
        retrieveSharingArticle(dto, createArticleDto.getSharingArticleId());
        return convert(articleCommandService.create(vo));
    }

    public Optional<ArticleDto> find(Long id) {
        return articleService.findById(id)
                .map(vo -> {
                    final var dto = convert(vo);
                    retrieveSharingArticle(dto, vo.getSharingArticleId());
                    return dto;
                });
    }

    public List<ArticleDto> findByIds(Set<Long> ids) {
        return articleService.findByIds(ids)
                .stream()
                .map(vo -> {
                    final var dto = convert(vo);
                    retrieveSharingArticle(dto, vo.getSharingArticleId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    void retrieveSharingArticle(ArticleDto dto, @Nullable Long sharingArticleId) {
        if (sharingArticleId == null) {
            return;
        }
        final var sharingArticle = articleService.findById(sharingArticleId);
        sharingArticle.ifPresent(a -> dto.setSharingArticle(convert(a)));
    }

    ArticleDto convert(IArticleVo articleVo) {
        final var dto = new ArticleDto();
        CachedBeanCopier.copy(articleVo, dto);
        posterFacade.find(articleVo.getPosterId()).ifPresent(dto::setPoster);
        return dto;
    }
}
