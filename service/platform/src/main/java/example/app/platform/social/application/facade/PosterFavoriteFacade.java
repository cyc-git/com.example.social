package example.app.platform.social.application.facade;

import example.app.domain.social.poster.favorite.IPosterFavoriteVo;
import example.app.domain.social.poster.favorite.PosterFavoriteService;
import example.app.domain.social.poster.favorite.command.PosterFavoriteCommandService;
import example.app.platform.social.application.dto.output.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PosterFavoriteFacade {
    private final PosterFavoriteService posterFavoriteService;
    private final PosterFavoriteCommandService posterFavoriteCommandService;
    private final ArticleFacade articleFacade;

    public List<ArticleDto> findFavoriteArticles(Long posterId) {
        final var articleIds = posterFavoriteService.findByPosterId(posterId)
                .stream()
                .map(IPosterFavoriteVo::getArticleId)
                .collect(Collectors.toSet());

        if (articleIds.isEmpty()) {
            return List.of();
        }

        return articleFacade.findByIds(articleIds);
    }

    public void favorite(Long posterId, Long articleId) {
        posterFavoriteCommandService.favorite(posterId, articleId);
    }

    public void unfavorite(Long posterId, Long articleId) {
        posterFavoriteCommandService.unfavorite(posterId, articleId);
    }
}
