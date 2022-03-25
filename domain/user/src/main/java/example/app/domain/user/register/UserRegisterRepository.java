package example.app.domain.user.register;

import example.app.domain.user.UserVo;

public interface UserRegisterRepository {

    UserVo register(RegisterUserVo registerUserVo, long time);

    boolean isAccountUsed(String account);
}
