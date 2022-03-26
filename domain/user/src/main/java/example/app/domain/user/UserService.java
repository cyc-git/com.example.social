package example.app.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RequiredArgsConstructor
@Validated
@Service
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(
            cacheNames = UserCacheName.SINGLE_BY_ACCOUNT,
            key = "#account.toLowerCase()"
    )
    public Optional<IUserVo> findByAccount(@NotBlank String account) {
        return userRepository.findByAccount(account).map(u -> u);
    }
}
