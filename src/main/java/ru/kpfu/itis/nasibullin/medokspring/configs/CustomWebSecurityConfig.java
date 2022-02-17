package ru.kpfu.itis.nasibullin.medokspring.configs;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kpfu.itis.nasibullin.medokspring.filters.AuthFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .formLogin().loginPage("/auth").loginProcessingUrl("/auth").usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/profile").failureUrl("/auth?error")
          .and()
          .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).invalidateHttpSession(true).deleteCookies("JSESSIONID")
          .and()
          .authorizeRequests().antMatchers("/*").permitAll()
          .and()
          .addFilterAfter(new AuthFilter(), BasicAuthenticationFilter.class)
          .csrf().disable();
    }
}
