package example.app.common.event.user;

import example.app.core.event.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRegisteredEvent extends Event {
    private Long id;
    private String name;
    private String account;
}
