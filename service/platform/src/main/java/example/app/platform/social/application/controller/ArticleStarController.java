package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.social.application.dto.output.PosterDto;
import example.app.platform.social.application.facade.ArticleStarFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Article Star", description = "the article star api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/article/star")
public class ArticleStarController {
    private final ArticleStarFacade articleStarFacade;

    @Operation(summary = "find all posters who stared the article")
    @GetMapping("all-stared-posters")
    public ResponseDto<List<PosterDto>> findAllStaredPosters(
            @RequestParam Long articleId
    ) {
        return ResponseDto.of(articleStarFacade.findStaredPosters(articleId));
    }

    @Operation(summary = "start an article")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void star(
            @RequestParam Long articleId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        articleStarFacade.star(articleId, currentPosterId);
    }

    @Operation(summary = "unstart an article")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void unstar(
            @RequestParam Long articleId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        articleStarFacade.unstar(articleId, currentPosterId);
    }
}
