package example.app.platform.user.application.controller;

import example.app.common.web.dto.ResponseDto;
import example.app.platform.user.application.dto.output.UserDto;
import example.app.platform.user.application.dto.input.RegisterUserDto;
import example.app.platform.user.application.facade.UserRegisterFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/register")
public class UserRegisterController {
    private final UserRegisterFacade userRegisterFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<UserDto> register(
            @RequestBody RegisterUserDto registerUserDto
    ) {
        return ResponseDto.of(userRegisterFacade.register(registerUserDto));
    }
}
