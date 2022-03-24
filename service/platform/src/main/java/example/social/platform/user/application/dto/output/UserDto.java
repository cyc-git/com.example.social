package example.social.platform.user.application.dto.output;

import example.social.common.core.util.CachedBeanCopier;
import example.social.domain.user.IUserVo;
import example.social.domain.user.UserVo;
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
