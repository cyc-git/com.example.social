package example.app.platform.infrastructure;

import example.app.core.data.jooq.IncludeDataJooq;
import example.app.core.system.IncludeSystem;
import example.app.common.web.security.IncludeCommonSecurity;
import org.springframework.context.annotation.Configuration;

@IncludeCommonSecurity
@IncludeDataJooq
@IncludeSystem
@Configuration
public class PlatformConfig {
}
