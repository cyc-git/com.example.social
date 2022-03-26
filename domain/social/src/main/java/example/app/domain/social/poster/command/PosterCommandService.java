package example.app.domain.social.poster.command;

import example.app.core.i18n.I18nAsserts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Validated
@Service
public class PosterCommandService {
    private final PosterCommandRepository posterCommandRepository;

    public void create(@NotNull @Valid CreatePosterVo createPosterVo) {
        I18nAsserts.isFalse(
                posterCommandRepository.isIdOrAccountUsed(createPosterVo.getId(), createPosterVo.getAccount()),
                PosterCommandI18nKey.ALREADY_EXISTS
        );
        posterCommandRepository.create(createPosterVo);
    }
}
