package example.app.domain.social.article;

import lombok.RequiredArgsConstructor;
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
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Optional<IArticleVo> findById(@NotNull Long id) {
        return articleRepository.findById(id).map(a -> a);
    }

    public List<IArticleVo> findByIds(@NotEmpty Set<@NotNull Long> ids) {
        return Collections.unmodifiableList(articleRepository.findByIds(ids));
    }
}
