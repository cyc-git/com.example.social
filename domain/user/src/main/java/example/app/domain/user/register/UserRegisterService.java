package example.app.domain.user.register;

import example.app.core.i18n.exception.I18nIllegalArgumentException;
import example.app.domain.user.IUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Clock;

@RequiredArgsConstructor
@Validated
@Service
public class UserRegisterService {
    private final UserRegisterRepository userRegisterRepository;
    private final PasswordEncoder passwordEncoder;
    private final Clock clock;

    public IUserVo register(@NotNull @Valid RegisterUserVo registerUserVo) {
        if (!isAccountUsed(registerUserVo.getAccount())) {
            registerUserVo.setPassword(passwordEncoder.encode(registerUserVo.getPassword()));
            return userRegisterRepository.register(registerUserVo, clock.millis());
        }

        throw new I18nIllegalArgumentException(UserRegisterI18nKey.FAILED);
    }

    public boolean isAccountUsed(@NotBlank @Size(max = 255) String account) {
        return userRegisterRepository.isAccountUsed(account);
    }
}
