package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.social.application.dto.output.PosterDto;
import example.app.platform.social.application.facade.PosterFollowFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/poster/follow")
public class PosterFollowController {
    private final PosterFollowFacade posterFollowFacade;

    @GetMapping("all-followers")
    public ResponseDto<List<PosterDto>> findFollower(
            @AuthenticationPrincipal Long currentPosterId
    ) {
        return ResponseDto.of(posterFollowFacade.findFollower(currentPosterId));
    }

    @GetMapping("all-followed")
    public ResponseDto<List<PosterDto>> findFollowed(
            @AuthenticationPrincipal Long currentPosterId
    ) {
        return ResponseDto.of(posterFollowFacade.findFollowed(currentPosterId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void follow(
            @RequestParam Long posterId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        posterFollowFacade.follow(posterId, currentPosterId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void unfollow(
            @RequestParam Long posterId,
            @AuthenticationPrincipal Long currentPosterId
    ) {
        posterFollowFacade.unfollow(posterId, currentPosterId);
    }
}
