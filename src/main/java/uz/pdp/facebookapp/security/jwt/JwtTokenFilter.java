package uz.pdp.facebookapp.security.jwt;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.facebookapp.entity.UserRole;
import uz.pdp.facebookapp.security.SecurityConfiguration;
import uz.pdp.facebookapp.security.jwt.JwtTokenProvider;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@NonNullApi
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null || !token.startsWith(BEARER)){
            filterChain.doFilter(request,response);
            return;
        }
        token = token.split(" ")[1];
        if (!jwtTokenProvider.isValid(token)){
            filterChain.doFilter(request,response);
            return;
        }
        Claims claims = jwtTokenProvider.parseAllClaims(token);
        List<LinkedHashMap<String, String>> rolesMapList = (List<LinkedHashMap<String, String>>) claims.get("roles");

        List<UserRole> roles = rolesMapList.stream()
                .map(roleMap -> {
                    UserRole role = new UserRole();
                    role.setName(roleMap.get("name"));
                    return role;
                })
                .toList();
        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                grantedAuthorities
                ));
        filterChain.doFilter(request,response);
    }
}
