package example.app.platform.social.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.domain.social.poster.PosterVo;
import example.app.platform.social.application.facade.PosterFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/poster")
public class PosterController {
    private final PosterFacade posterFacade;

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto<PosterVo>> find(@PathVariable Long id) {
        return ResponseEntity.of(posterFacade.find(id).map(ResponseDto::of));
    }
}
