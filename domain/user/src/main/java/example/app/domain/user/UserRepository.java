package example.app.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<UserVo> findByAccount(String account);
}
