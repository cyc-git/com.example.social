package example.social.domain.user.auth;

import example.social.common.core.i18n.exception.I18nForbiddenException;
import example.social.domain.user.IUserVo;
import example.social.domain.user.UserService;
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
