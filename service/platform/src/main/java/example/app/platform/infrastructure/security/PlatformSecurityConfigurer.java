package example.app.platform.infrastructure.security;

import example.app.common.web.security.AbstractSecurityConfigurer;
import example.app.domain.social.poster.PosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class PlatformSecurityConfigurer extends AbstractSecurityConfigurer {
    private final PosterService posterService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers(
                        "/user/auth/login**",
                        "/user/register**"
                )
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new SimpleUserAuthenticateFilter(posterService), UsernamePasswordAuthenticationFilter.class);
    }
}
