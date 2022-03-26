package example.app.platform.social.application.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.article.ArticleVo;
import example.app.domain.social.article.IArticleVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends ArticleVo {

    public ArticleDto(IArticleVo articleVo) {
        CachedBeanCopier.copy(articleVo, this);
    }
}
