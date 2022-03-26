package example.app.platform.user.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.user.application.dto.output.UserDto;
import example.app.platform.user.application.facade.UserAuthFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "User Auth", description = "the user auth api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/auth")
public class UserAuthController {
    private final UserAuthFacade userAuthFacade;

    @Operation(summary = "login as an already exists user")
    @PostMapping("/login")
    public ResponseDto<UserDto> login(
            String account,
            String password,
            HttpServletResponse response
    ) {
        final var user = userAuthFacade.login(account, password);

        final var cookie = new Cookie("userId", String.valueOf(user.getId()));
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseDto.of(user);
    }

    @Operation(summary = "logout")
    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        final var cookie = new Cookie("userId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
