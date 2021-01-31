package pl.coderslab.charity.security;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final DataSource dataSource;
        private final UserDetailsService userDetailsService;

        public UserConfigurationAdapter(DataSource dataSource, UserDetailsService userDetailsService) {
            this.dataSource = dataSource;
            this.userDetailsService = userDetailsService;
        }

        public PasswordEncoder encoder() {
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
                    .antMatchers("/user/**")
                    .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/", "/register/**")
                    .permitAll()

                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/user/my-donations")

                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .logoutSuccessUrl("/");
        }

    }

    @Configuration
    @Order(2)
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final DataSource dataSource;
        private final UserDetailsService userDetailsService;

        public AdminConfigurationAdapter(DataSource dataSource, UserDetailsService userDetailsService) {
            this.dataSource = dataSource;
            this.userDetailsService = userDetailsService;
        }

        public PasswordEncoder encoder() {
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
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")

                    .and()
                    .formLogin()
                    .loginPage("/admin/login")
                    .permitAll()
//                    .loginProcessingUrl("/admin_login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/admin/dashboard", true)

                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
                    .logoutSuccessUrl("/")

                    .and()
                    .csrf().disable();
        }

    }


}
