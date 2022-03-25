package example.app.domain.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface IUserVo {
    @NotNull
    Long getId();

    @NotBlank
    @Size(max = 100)
    String getName();

    @NotBlank
    @Size(max = 255)
    String getAccount();

    @NotNull
    Long getCreatedAt();

    @NotNull
    Long getUpdatedAt();
}
