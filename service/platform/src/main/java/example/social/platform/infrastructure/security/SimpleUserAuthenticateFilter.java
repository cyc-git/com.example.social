package example.social.platform.infrastructure.security;

import example.social.domain.user.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SimpleUserAuthenticateFilter extends OncePerRequestFilter {
    private final UserService userService;

    protected SimpleUserAuthenticateFilter(UserService userService) {
        this.userService = userService;
    }

    public void attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
        Optional.ofNullable(request.getCookies())
                .stream()
                .flatMap(Arrays::stream)
                .filter(c -> Objects.equals(c.getName(), "userAccount"))
                .findAny()
                .map(Cookie::getValue)
                .flatMap(userService::findByAccount)
                .ifPresent(iUserVo ->
                        SecurityContextHolder.getContext()
                                .setAuthentication(new UsernamePasswordAuthenticationToken(iUserVo, null, List.of()))
                );
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        attemptAuthentication(request);
        filterChain.doFilter(request, response);
    }
}
