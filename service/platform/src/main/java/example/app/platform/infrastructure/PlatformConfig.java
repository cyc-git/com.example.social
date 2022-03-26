package example.app.platform.infrastructure;

import example.app.core.data.jooq.IncludeDataJooq;
import example.app.core.event.IncludeEvent;
import example.app.core.system.IncludeSystem;
import example.app.common.web.security.IncludeCommonSecurity;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@IncludeCommonSecurity
@IncludeDataJooq
@IncludeEvent
@IncludeSystem
@EnableCaching
@Configuration
public class PlatformConfig {
}
