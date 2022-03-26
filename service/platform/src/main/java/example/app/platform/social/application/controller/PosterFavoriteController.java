package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.social.application.dto.output.ArticleDto;
import example.app.platform.social.application.facade.PosterFavoriteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/poster/favorite")
public class PosterFavoriteController {
    private final PosterFavoriteFacade posterFavoriteFacade;

    @GetMapping("all")
    public ResponseDto<List<ArticleDto>> findAll(
            @AuthenticationPrincipal Long currentPosterId
    ) {
        return ResponseDto.of(posterFavoriteFacade.findFavoriteArticles(currentPosterId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void favorite(
            @RequestParam Long articleId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        posterFavoriteFacade.favorite(currentPosterId, articleId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void unfavorite(
            @RequestParam Long articleId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        posterFavoriteFacade.unfavorite(currentPosterId, articleId);
    }
}
