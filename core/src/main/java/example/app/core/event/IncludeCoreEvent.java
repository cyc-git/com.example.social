package example.app.core.event;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        SimpleEventPublisher.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface IncludeCoreEvent {
}
