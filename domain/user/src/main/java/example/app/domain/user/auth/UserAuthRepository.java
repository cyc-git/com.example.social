package example.app.domain.user.auth;

import java.util.Optional;

public interface UserAuthRepository {

    Optional<String> findPassword(long userId);
}
