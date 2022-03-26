package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.domain.social.poster.IPosterVo;
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
            @AuthenticationPrincipal IPosterVo posterVo
    ) {
        return ResponseDto.of(posterFollowFacade.findFollower(posterVo.getId()));
    }

    @GetMapping("all-followed")
    public ResponseDto<List<PosterDto>> findFollowed(
            @AuthenticationPrincipal IPosterVo posterVo
    ) {
        return ResponseDto.of(posterFollowFacade.findFollowed(posterVo.getId()));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void follow(
            @RequestParam Long posterId,
            @AuthenticationPrincipal IPosterVo posterVo
    ) {
        posterFollowFacade.follow(posterId, posterVo.getId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void unfollow(
            @RequestParam Long posterId,
            @AuthenticationPrincipal IPosterVo posterVo
    ) {
        posterFollowFacade.unfollow(posterId, posterVo.getId());
    }
}
