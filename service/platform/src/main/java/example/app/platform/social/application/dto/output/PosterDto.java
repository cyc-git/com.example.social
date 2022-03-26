package example.app.platform.social.application.dto.output;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.poster.IPosterVo;
import example.app.domain.social.poster.PosterVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PosterDto extends PosterVo {

    public PosterDto(IPosterVo posterVo) {
        CachedBeanCopier.copy(posterVo, this);
    }
}
