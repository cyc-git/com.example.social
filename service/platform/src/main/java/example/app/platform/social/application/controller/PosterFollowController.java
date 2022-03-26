package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.social.application.dto.output.PosterDto;
import example.app.platform.social.application.facade.PosterFollowFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Poster Follow", description = "the poster follow api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/poster/follow")
public class PosterFollowController {
    private final PosterFollowFacade posterFollowFacade;

    @Operation(summary = "find all posters who has followed current poster")
    @GetMapping("all-followers")
    public ResponseDto<List<PosterDto>> findFollower(
            @AuthenticationPrincipal Long currentPosterId
    ) {
        return ResponseDto.of(posterFollowFacade.findFollower(currentPosterId));
    }

    @Operation(summary = "find all poster who the current poster has followed")
    @GetMapping("all-followed")
    public ResponseDto<List<PosterDto>> findFollowed(
            @AuthenticationPrincipal Long currentPosterId
    ) {
        return ResponseDto.of(posterFollowFacade.findFollowed(currentPosterId));
    }

    @Operation(summary = "follow a poster")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void follow(
            @RequestParam Long posterId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        posterFollowFacade.follow(posterId, currentPosterId);
    }

    @Operation(summary = "unfollow a poster")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void unfollow(
            @RequestParam Long posterId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        posterFollowFacade.unfollow(posterId, currentPosterId);
    }
}
