package example.app.domain.social.poster;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface IPosterVo {

    @NotNull
    Long getId();

    @NotBlank
    @Size(max = 100)
    String getName();

    @NotBlank
    @Size(max = 255)
    String getAccount();

    @NotNull
    Integer getFollowerCount();

    @NotNull
    Integer getFollowedCount();

    @NotNull
    Integer
    getFavoritedCount();
}
