package example.app.platform.user.application.dto.input;

import example.app.domain.user.register.RegisterUserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterUserDto extends RegisterUserVo {
    @Schema(description = "the name of user", required = true)
    @Override
    public String getName() {
        return super.getName();
    }

    @Schema(description = "the account of user", required = true)
    @Override
    public String getAccount() {
        return super.getAccount();
    }

    @Schema(description = "the password of user", required = true)
    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
