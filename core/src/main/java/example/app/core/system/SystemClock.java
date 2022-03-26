package example.app.core.system;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class SystemClock extends Clock {
    private final Clock system = Clock.system(ZoneId.systemDefault());

    @Override
    public ZoneId getZone() {
        return system.getZone();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return system.withZone(zone);
    }

    @Override
    public Instant instant() {
        return system.instant();
    }
}
