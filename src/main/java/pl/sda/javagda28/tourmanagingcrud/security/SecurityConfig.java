package pl.sda.javagda28.tourmanagingcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bands-only").hasRole("BAND")
                .antMatchers("/admins-only").hasRole("ADMIN")
                .antMatchers("/organiser-only").hasRole("ORGANISER")
                .antMatchers("/everyone", "/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login-error").permitAll();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("admin").password("admin").roles("ADMIN", "ORGANISER", "BAND" ).build());
        manager.createUser(users.username("organiser").password("user").roles("ORGANISER").build());
        manager.createUser(users.username("band").password("user").roles("BAND").build());
        return manager;
    }
}
