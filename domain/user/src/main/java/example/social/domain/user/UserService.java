package example.social.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RequiredArgsConstructor
@Validated
@Service
public class UserService {
    private final UserRepository userRepository;

    public Optional<IUserVo> findByAccount(@NotBlank String account) {
        return userRepository.findByAccount(account).map(u -> u);
    }
}
