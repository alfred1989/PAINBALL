package pinball.pinball.MeinConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MeinConfing  extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("marcinS")
                .password("marcinGostynS")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails, admin);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
               // .antMatchers("/static/").permitAll()
                .antMatchers("/", "/IMG").permitAll()
                .antMatchers("/allComment", "/message").permitAll()
                .antMatchers("/admin","/deleteComment","/editDates", "/addImg","/images").hasRole("ADMIN")
              .antMatchers("/resources/**").permitAll().anyRequest().permitAll()


                .anyRequest().hasRole("ADMIN")
                .and()


                .formLogin().permitAll();
    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**").anyRequest();
//    }

}