package example.social.common.core.data.jooq;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        JooqConfig.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeCommonDataJooq {
}
