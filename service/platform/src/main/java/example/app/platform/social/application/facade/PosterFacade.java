package example.app.platform.social.application.facade;

import example.app.domain.social.poster.PosterService;
import example.app.platform.social.application.dto.output.PosterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PosterFacade {
    private final PosterService posterService;

    public Optional<PosterDto> find(Long id) {
        return posterService.findById(id).map(PosterDto::new);
    }

    public List<PosterDto> findByIds(Set<Long> ids) {
        return posterService.findByIds(ids)
                .stream()
                .map(PosterDto::new)
                .collect(Collectors.toList());
    }
}
