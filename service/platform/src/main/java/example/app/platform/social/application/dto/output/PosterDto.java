package example.app.platform.social.application.dto.output;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.poster.IPosterVo;
import example.app.domain.social.poster.PosterVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Schema
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PosterDto extends PosterVo {
    public PosterDto(IPosterVo posterVo) {
        CachedBeanCopier.copy(posterVo, this);
    }

    @Schema(description = "id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Schema(description = "the name of this poster")
    @Override
    public String getName() {
        return super.getName();
    }

    @Schema(description = "the account of this poster")
    @Override
    public String getAccount() {
        return super.getAccount();
    }

    @Schema(description = "the count of posters who followed this poster")
    @Override
    public Integer getFollowerCount() {
        return super.getFollowerCount();
    }

    @Schema(description = "the count of posters who this poster followed")
    @Override
    public Integer getFollowedCount() {
        return super.getFollowedCount();
    }

    @Schema(description = "the count of article which this poster favorited")
    @Override
    public Integer getFavoritedCount() {
        return super.getFavoritedCount();
    }
}
