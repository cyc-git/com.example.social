package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.social.application.dto.input.CreateArticleReplyDto;
import example.app.platform.social.application.dto.output.ArticleReplyAggregateDto;
import example.app.platform.social.application.dto.output.ArticleReplyDto;
import example.app.platform.social.application.facade.ArticleReplyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article/reply")
public class ArticleReplyController {
    private final ArticleReplyFacade articleReplyFacade;

    @GetMapping("by-article-id")
    public ResponseDto<ArticleReplyAggregateDto> findByArticleId(
            @RequestParam Long articleId
    ) {
        return ResponseDto.of(articleReplyFacade.findByArticleId(articleId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<ArticleReplyDto> reply(
            @RequestBody CreateArticleReplyDto createArticleReplyDto,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        return ResponseDto.of(articleReplyFacade.reply(createArticleReplyDto, currentPosterId));
    }
}
