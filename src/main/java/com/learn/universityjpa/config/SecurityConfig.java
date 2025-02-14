package com.learn.universityjpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class SecurityConfig
 * для конфигурации security
 * http://localhost:8082/groups
 */
// localhost:8081
// localhost:8082/manager.html
// localhost:8082/user.html
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
      http.oauth2Login(Customizer.withDefaults());

      return http
              .authorizeHttpRequests(c -> c
                      .antMatchers("/public/**").permitAll()
                      .antMatchers("/groups").hasAnyRole("USER", "MANAGER", "ADMIN")
                      .antMatchers("/students").hasAnyRole("USER", "MANAGER", "ADMIN")
                      .antMatchers("/subjects").hasAnyRole("USER", "MANAGER", "ADMIN")
                      .antMatchers("/teachers").hasAnyRole("USER", "MANAGER", "ADMIN")
                      .antMatchers("/manager.html").hasRole("MANAGER")
                      .antMatchers("/user.html").hasRole("USER")
                      .anyRequest().authenticated())
              .build();
  }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        converter.setPrincipalClaimName("preferred_username");

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> roles = getRoles(jwt
                    .getClaim("resource_access")
                    .toString());

            var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);

            return Stream.concat(authorities.stream(),
                            roles.stream()
                                    .filter(role -> role.startsWith("ROLE_"))
                                    .map(SimpleGrantedAuthority::new)
                                    .map(GrantedAuthority.class::cast))
                    .toList();
        });

        return converter;
    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {
        var oidcUserService = new OidcUserService();
        return userRequest -> {
            var oidcUser = oidcUserService.loadUser(userRequest);

            List<String> roles = getRoles(oidcUser.toString());

            var authorities = Stream.concat(oidcUser.getAuthorities().stream(),
                            roles.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .map(GrantedAuthority.class::cast))
                    .toList();

            return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }

    private List<String> getRoles(String s) {
        List<String> commonRoles = Arrays.asList("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN");
        List<String> roles = new ArrayList<>();
        commonRoles.forEach(r-> {
            if (s.contains(r)) {
                roles.add(r);
            }
        });
        return roles;
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
