package com.coduck.web.config;

import com.coduck.web.domain.user.User;
import com.coduck.web.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by YG-MAC on 2018. 3. 5..
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .headers()
                .frameOptions().sameOrigin();
    }

    //TODO: 추가적인 권한이 필요할 때가 있을까?
    @Bean
    public AuthoritiesExtractor authoritiesExtractor() {
        return map -> AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Bean
    public PrincipalExtractor principalExtractor() {
        return map -> {
            String id = (String) map.get("login");
            return userRepository.findByGithubId(id)
                    .orElseGet(() -> userRepository.save(new User(id)));
        };
    }
}
