package example.app.domain.user.register;

import example.app.common.event.user.UserRegisteredEvent;
import example.app.core.event.EventPublisher;
import example.app.core.i18n.exception.I18nIllegalArgumentException;
import example.app.core.util.CachedBeanCopier;
import example.app.domain.user.IUserVo;
import example.app.domain.user.UserCacheName;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Clock;

@RequiredArgsConstructor
@Validated
@Service
public class UserRegisterService {
    private final UserRegisterRepository userRegisterRepository;
    private final PasswordEncoder passwordEncoder;
    private final Clock clock;
    private final EventPublisher eventPublisher;

    @CacheEvict(
            cacheNames = UserCacheName.SINGLE_BY_ACCOUNT,
            key = "#result.account.toLowerCase()"
    )
    public IUserVo register(@NotNull @Valid RegisterUserVo registerUserVo) {
        if (!userRegisterRepository.isAccountUsed(registerUserVo.getAccount())) {
            registerUserVo.setPassword(passwordEncoder.encode(registerUserVo.getPassword()));
            final var user = userRegisterRepository.register(registerUserVo, clock.millis());
            publishUserRegisteredEvent(user);
            return user;
        }

        throw new I18nIllegalArgumentException(UserRegisterI18nKey.FAILED);
    }

    void publishUserRegisteredEvent(IUserVo userVo) {
        final var event = new UserRegisteredEvent();
        CachedBeanCopier.copy(userVo, event);
        eventPublisher.publish(event);
    }
}
