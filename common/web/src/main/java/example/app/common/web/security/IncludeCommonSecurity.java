package example.app.common.web.security;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        SecurityConfig.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeCommonSecurity {
}
