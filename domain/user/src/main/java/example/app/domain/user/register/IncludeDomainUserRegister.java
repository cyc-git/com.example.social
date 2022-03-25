package example.app.domain.user.register;

import example.app.domain.user.infrastructure.data.repository.UserRegisterRepositoryImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        UserRegisterService.class,
        UserRegisterRepositoryImpl.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeDomainUserRegister {
}
