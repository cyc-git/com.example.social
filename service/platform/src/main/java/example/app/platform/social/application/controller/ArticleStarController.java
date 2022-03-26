package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.domain.social.poster.IPosterVo;
import example.app.platform.social.application.dto.output.PosterDto;
import example.app.platform.social.application.facade.ArticleStarFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article/star")
public class ArticleStarController {
    private final ArticleStarFacade articleStarFacade;

    @GetMapping("all-stared-posters")
    public ResponseDto<List<PosterDto>> findAllStaredPosters(
            @RequestParam Long articleId
    ) {
        return ResponseDto.of(articleStarFacade.findStaredPosters(articleId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void star(
            @RequestParam Long articleId,
            @AuthenticationPrincipal IPosterVo posterVo
    ) {
        articleStarFacade.star(articleId, posterVo.getId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void unstar(
            @RequestParam Long articleId,
            @AuthenticationPrincipal IPosterVo posterVo
    ) {
        articleStarFacade.unstar(articleId, posterVo.getId());
    }
}
