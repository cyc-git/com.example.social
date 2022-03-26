package example.app.platform.social.application.facade;

import example.app.domain.social.poster.PosterService;
import example.app.platform.social.application.dto.output.PosterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PosterFacade {
    private final PosterService posterService;

    public Optional<PosterDto> find(Long id) {
        return posterService.findById(id).map(PosterDto::new);
    }
}
