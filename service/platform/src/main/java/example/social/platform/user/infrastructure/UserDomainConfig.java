package example.social.platform.user.infrastructure;

import example.social.domain.user.IncludeDomainUser;
import example.social.domain.user.auth.IncludeDomainUserAuth;
import example.social.domain.user.register.IncludeDomainUserRegister;
import org.springframework.context.annotation.Configuration;

@IncludeDomainUserRegister
@IncludeDomainUserAuth
@IncludeDomainUser
@Configuration
public class UserDomainConfig {
}
