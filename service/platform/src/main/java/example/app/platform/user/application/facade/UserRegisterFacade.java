package example.app.platform.user.application.facade;

import example.app.domain.user.register.UserRegisterService;
import example.app.platform.user.application.dto.output.UserDto;
import example.app.platform.user.application.dto.input.RegisterUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegisterFacade {
    private final UserRegisterService userRegisterService;

    public UserDto register(RegisterUserDto registerUserDto) {
        return UserDto.from(userRegisterService.register(registerUserDto));
    }
}
