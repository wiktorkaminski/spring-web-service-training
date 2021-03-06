package pl.coderslab.charity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter{

        private final DataSource dataSource;
        private final UserDetailsService userDetailsService;

        public UserConfigurationAdapter(DataSource dataSource, UserDetailsService userDetailsService) {
            this.dataSource = dataSource;
            this.userDetailsService = userDetailsService;
        }


        private PasswordEncoder encoder() {
            return new BCryptPasswordEncoder();
        }


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth
                    .jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(
                            "SELECT email, password, enabled FROM users WHERE email=?"
                    )
                    .authoritiesByUsernameQuery(
                            "SELECT email, authority FROM authorities WHERE email=?"
                    )
                    .passwordEncoder(encoder())
            ;
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(encoder());

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/donations/**", "/user/**")
                    .hasRole("USER")
                    .antMatchers("/", "/register/**")
                    .permitAll()
                    .and()
                    .formLogin().loginPage("/login")
                    .usernameParameter("email")
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .logoutSuccessUrl("/");
        }

    }

}
