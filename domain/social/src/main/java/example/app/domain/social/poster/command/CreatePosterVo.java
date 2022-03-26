package example.app.domain.social.poster.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreatePosterVo {
    @NotNull
    private Long id;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 255)
    private String account;
}
