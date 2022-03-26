package example.app.core.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

/**
 * Simply publish event into spring event bus, this class is not designed for real world use.
 */
@RequiredArgsConstructor
public class SimpleEventPublisher implements EventPublisher {
    private final ApplicationContext applicationContext;

    @Override
    public void publish(Event event) {
        event.setEventId(UUID.randomUUID().toString());
        applicationContext.publishEvent(event);
    }
}
