package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.domain.social.poster.PosterVo;
import example.app.platform.social.application.facade.PosterFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Poster", description = "the poster api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/poster")
public class PosterController {
    private final PosterFacade posterFacade;

    @Operation(summary = "find a poster")
    @GetMapping("{id}")
    public ResponseEntity<ResponseDto<PosterVo>> find(@PathVariable Long id) {
        return ResponseEntity.of(posterFacade.find(id).map(ResponseDto::of));
    }
}
