package example.app.platform.user.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.user.application.dto.output.UserDto;
import example.app.platform.user.application.facade.UserAuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/auth")
public class UserAuthController {
    private final UserAuthFacade userAuthFacade;

    @PostMapping("/login")
    public ResponseDto<UserDto> login(
            String account,
            String password,
            HttpServletResponse response
    ) {
        final var user = userAuthFacade.login(account, password);

        final var cookie = new Cookie("userAccount", user.getAccount());
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseDto.of(user);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        final var cookie = new Cookie("userAccount", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
