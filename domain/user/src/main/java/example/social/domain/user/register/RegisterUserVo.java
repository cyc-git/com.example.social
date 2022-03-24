package example.social.domain.user.register;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterUserVo {
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 255)
    private String account;
    @NotBlank
    @Size(max = 100)
    private String password;
}
