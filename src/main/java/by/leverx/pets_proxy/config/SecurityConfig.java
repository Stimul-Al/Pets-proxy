package by.leverx.pets_proxy.config;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.extractor.IasXsuaaExchangeBroker;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import com.sap.cloud.security.xsuaa.tokenflows.XsuaaTokenFlows;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

/**
 *
 * @author aliaksei.babashau
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final XsuaaServiceConfiguration xsuaaServiceConfiguration;

    private final XsuaaTokenFlows xsuaaTokenFlows;

    @Override protected void configure(HttpSecurity http) throws Exception {
        http
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(DELETE).hasAuthority("Delete")
                    .antMatchers(PUT).hasAuthority("Edit")
                    .antMatchers(POST).hasAuthority("Edit")
                    .antMatchers(GET).hasAuthority("Read")
                .and()
                    .oauth2ResourceServer()
                    .bearerTokenResolver(new IasXsuaaExchangeBroker(xsuaaTokenFlows))
                    .jwt()
                    .jwtAuthenticationConverter(getJwtAuthenticationConverter());
    }

    private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true);
        return converter;
    }
}