package example.app.platform.social.application.facade;

import example.app.domain.social.article.star.ArticleStarService;
import example.app.domain.social.article.star.IArticleStarVo;
import example.app.domain.social.article.star.command.ArticleStarCommandService;
import example.app.platform.social.application.dto.output.PosterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleStarFacade {
    private final ArticleStarService articleStarService;
    private final ArticleStarCommandService articleStarCommandService;
    private final PosterFacade posterFacade;

    public List<PosterDto> findStaredPosters(Long articleId) {
        final var posterIds = articleStarService.findByArticleId(articleId)
                .stream()
                .map(IArticleStarVo::getStaredBy)
                .collect(Collectors.toSet());

        if (posterIds.isEmpty()) {
            return List.of();
        }

        return posterFacade.findByIds(posterIds);
    }

    public void star(Long articleId, Long posterId) {
        articleStarCommandService.star(articleId, posterId);
    }

    public void unstar(Long articleId, Long posterId) {
        articleStarCommandService.unstar(articleId, posterId);
    }
}
