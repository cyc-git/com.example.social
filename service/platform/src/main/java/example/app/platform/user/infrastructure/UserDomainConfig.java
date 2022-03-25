package example.app.platform.user.infrastructure;

import example.app.domain.user.IncludeDomainUser;
import example.app.domain.user.auth.IncludeDomainUserAuth;
import example.app.domain.user.register.IncludeDomainUserRegister;
import org.springframework.context.annotation.Configuration;

@IncludeDomainUserRegister
@IncludeDomainUserAuth
@IncludeDomainUser
@Configuration
public class UserDomainConfig {
}
