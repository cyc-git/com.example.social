package example.app.platform.infrastructure;

import example.app.common.core.data.jooq.IncludeCommonDataJooq;
import example.app.common.core.system.IncludeCommonSystem;
import example.app.common.web.security.IncludeCommonSecurity;
import org.springframework.context.annotation.Configuration;

@IncludeCommonSecurity
@IncludeCommonDataJooq
@IncludeCommonSystem
@Configuration
public class PlatformConfig {
}
