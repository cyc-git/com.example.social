package example.app.domain.user.auth;

import example.app.common.core.i18n.exception.I18nForbiddenException;
import example.app.domain.user.UserService;
import example.app.domain.user.IUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Validated
@Service
public class UserAuthService {
    private final UserService userService;
    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public IUserVo authenticate(
            @NotBlank String account,
            @NotBlank String userPassword
    ) {
        final var userOpt = userService.findByAccount(account);

        if (userOpt.isPresent()) {
            final var user = userOpt.get();
            final var encodedPasswordOpt = userAuthRepository.findPassword(user.getId());
            if (encodedPasswordOpt.isPresent()) {
                if (passwordEncoder.matches(userPassword, encodedPasswordOpt.get())) {
                    return user;
                }
            }
        }

        throw new I18nForbiddenException(UserAuthI18nKey.FAILED);
    }
}
