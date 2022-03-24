package example.social.platform.user.application.facade;

import example.social.domain.user.auth.UserAuthService;
import example.social.platform.user.application.dto.output.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class UserAuthFacade {
    private final UserAuthService userAuthService;

    public UserDto login(String account, String password) {
        return UserDto.from(userAuthService.authenticate(account, password));
    }

    public void logout(HttpServletResponse response) {
        final var cookie = new Cookie("userAccount", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
