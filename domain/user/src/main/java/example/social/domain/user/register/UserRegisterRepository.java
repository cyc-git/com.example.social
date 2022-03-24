package example.social.domain.user.register;

import example.social.domain.user.UserVo;

public interface UserRegisterRepository {

    UserVo register(RegisterUserVo registerUserVo, long time);

    boolean isAccountUsed(String account);
}
