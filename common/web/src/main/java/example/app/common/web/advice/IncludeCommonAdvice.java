package example.app.common.web.advice;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        ConstraintViolationAdvice.class,
        I18nAdvice.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeCommonAdvice {
}
