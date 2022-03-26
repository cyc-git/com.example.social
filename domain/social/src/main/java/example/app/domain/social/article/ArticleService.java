package example.app.domain.social.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Optional<IArticleVo> findById(@NotNull Long id) {
        return articleRepository.findById(id).map(a -> a);
    }
}
