package example.social.platform.user.application.facade;

import example.social.domain.user.register.UserRegisterService;
import example.social.platform.user.application.dto.input.RegisterUserDto;
import example.social.platform.user.application.dto.output.UserDto;
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
