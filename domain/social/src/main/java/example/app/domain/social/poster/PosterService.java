package example.app.domain.social.poster;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Validated
@Service
public class PosterService {
    private final PosterRepository posterRepository;

    @Cacheable(PosterCacheName.SINGLE_BY_ID)
    public Optional<IPosterVo> findById(@NotNull Long id) {
        return posterRepository.findById(id).map(p -> p);
    }

    public List<IPosterVo> findByIds(@NotEmpty Set<@NotNull Long> ids) {
        return Collections.unmodifiableList(posterRepository.findByIds(ids));
    }
}
