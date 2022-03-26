package example.app.platform.infrastructure.security;

import example.app.domain.social.poster.PosterService;
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
    private final PosterService posterService;

    protected SimpleUserAuthenticateFilter(PosterService posterService) {
        this.posterService = posterService;
    }

    public void attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
        Optional.ofNullable(request.getCookies())
                .stream()
                .flatMap(Arrays::stream)
                .filter(c -> Objects.equals(c.getName(), "userAccount"))
                .findAny()
                .map(Cookie::getValue)
                .flatMap(posterService::findByAccount)
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
