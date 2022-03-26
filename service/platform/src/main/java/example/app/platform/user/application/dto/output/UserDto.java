package example.app.platform.user.application.dto.output;

import example.app.core.util.CachedBeanCopier;
import example.app.domain.user.IUserVo;
import example.app.domain.user.UserVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends UserVo {

    public static UserDto from(IUserVo userVo) {
        final var dto = new UserDto();
        CachedBeanCopier.copy(userVo, dto);
        return dto;
    }
}
