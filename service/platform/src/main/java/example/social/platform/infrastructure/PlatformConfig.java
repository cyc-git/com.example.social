package example.social.platform.infrastructure;

import example.social.common.core.data.jooq.IncludeCommonDataJooq;
import example.social.common.core.system.IncludeCommonSystem;
import example.social.common.web.security.IncludeCommonSecurity;
import org.springframework.context.annotation.Configuration;

@IncludeCommonSecurity
@IncludeCommonDataJooq
@IncludeCommonSystem
@Configuration
public class PlatformConfig {
}
