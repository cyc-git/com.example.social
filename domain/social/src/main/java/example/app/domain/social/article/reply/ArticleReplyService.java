package example.app.domain.social.article.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Validated
@Service
public class ArticleReplyService {
    private final ArticleReplyRepository articleReplyRepository;

    public List<IArticleReplyVo> findByArticleId(@NotNull Long articleId) {
        return Collections.unmodifiableList(articleReplyRepository.findByArticleId(articleId));
    }
}
