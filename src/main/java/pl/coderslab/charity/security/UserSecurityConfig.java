package pl.coderslab.charity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(1)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/user/**")
                .authorizeRequests()
                .antMatchers("/user/**")
                .hasAnyRole("USER", "ADMIN")

                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/user/my-donations")
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "GET"))
                .logoutSuccessUrl("/")

                .and().csrf().disable();
    }
}
