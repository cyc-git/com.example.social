package example.app.platform.user.application.dto.output;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.user.IUserVo;
import example.app.domain.user.UserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends UserVo {
    @Schema(description = "id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Schema(description = "the name of user")
    @Override
    public String getName() {
        return super.getName();
    }

    @Schema(description = "the account of user")
    @Override
    public String getAccount() {
        return super.getAccount();
    }

    @Schema(description = "the epoch time when this user was created")
    @Override
    public Long getCreatedAt() {
        return super.getCreatedAt();
    }

    @Schema(description = "the last epoch time when this user was updated")
    @Override
    public Long getUpdatedAt() {
        return super.getUpdatedAt();
    }

    public static UserDto from(IUserVo userVo) {
        final var dto = new UserDto();
        CachedBeanCopier.copy(userVo, dto);
        return dto;
    }
}
