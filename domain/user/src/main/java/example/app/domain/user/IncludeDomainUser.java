package example.app.domain.user;

import example.app.domain.user.infrastructure.data.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        UserService.class,
        UserRepositoryImpl.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeDomainUser {
}
