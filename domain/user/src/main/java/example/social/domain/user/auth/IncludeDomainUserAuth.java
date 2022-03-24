package example.social.domain.user.auth;

import example.social.domain.user.IncludeDomainUser;
import example.social.domain.user.infrastructure.data.repository.UserAuthRepositoryImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        UserAuthService.class,
        UserAuthRepositoryImpl.class
})
@IncludeDomainUser
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeDomainUserAuth {
}
