package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.domain.social.poster.IPosterVo;
import example.app.platform.social.application.dto.input.CreateArticleDto;
import example.app.platform.social.application.dto.output.ArticleAggregateDto;
import example.app.platform.social.application.dto.output.ArticleDto;
import example.app.platform.social.application.facade.ArticleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleFacade articleFacade;

    @PostMapping
    public ResponseDto<ArticleDto> create(
            @RequestBody CreateArticleDto createArticleDto,
            @AuthenticationPrincipal IPosterVo poster
    ) {
        return ResponseDto.of(articleFacade.create(createArticleDto, poster.getId()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto<ArticleAggregateDto>> find(@PathVariable Long id) {
        return ResponseEntity.of(articleFacade.findAggregate(id).map(ResponseDto::of));
    }
}
