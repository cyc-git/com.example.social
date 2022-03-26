package example.app.platform.social.infrastructure.consumer;

import example.app.common.event.user.UserRegisteredEvent;
import example.app.core.util.CachedBeanCopier;
import example.app.domain.social.poster.command.CreatePosterVo;
import example.app.domain.social.poster.command.PosterCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * demonstrated poster creation when a user registered, this class is not designed for real world use/
 */
@RequiredArgsConstructor
@Component
public class UserRegisteredEventConsumer {
    private final PosterCommandService posterCommandService;

    @EventListener
    public void consume(UserRegisteredEvent event) {
        final var createPosterVo = new CreatePosterVo();
        CachedBeanCopier.copy(event, createPosterVo);
        posterCommandService.create(createPosterVo);
    }
}
