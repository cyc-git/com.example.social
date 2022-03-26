package example.app.platform.user.application.facade;

import example.app.domain.user.auth.UserAuthService;
import example.app.platform.user.application.dto.output.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAuthFacade {
    private final UserAuthService userAuthService;

    public UserDto login(String account, String password) {
        return UserDto.from(userAuthService.authenticate(account, password));
    }
}
