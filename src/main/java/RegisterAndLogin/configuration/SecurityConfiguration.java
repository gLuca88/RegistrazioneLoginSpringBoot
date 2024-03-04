package RegisterAndLogin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import RegisterAndLogin.service.CustomUserDeatailsServices;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
     
	@Autowired
	CustomUserDeatailsServices customUserdetailsService;

    @Bean
    static PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain securityFiletrChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(requests -> requests.requestMatchers("/register").permitAll().requestMatchers("/home")
                .permitAll()).formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true).permitAll()).logout(logout -> logout.invalidateHttpSession(true)
                .clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll());

		return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(customUserdetailsService).passwordEncoder(passwordEncoder());
	}

}// close the configuration
