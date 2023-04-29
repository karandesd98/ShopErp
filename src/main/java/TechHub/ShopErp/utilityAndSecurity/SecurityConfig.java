package TechHub.ShopErp.utilityAndSecurity;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService getUserDetailService()
	{
		return new customeUserDetailServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	
	@Override  // for  authantication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}
	
	@Override  // for autherization
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/customer/**").hasRole("CUSTOMER")
        .antMatchers("/**").permitAll()
        .and().formLogin()
        .loginPage("/signin")
        .loginProcessingUrl("/doLogin")
        .defaultSuccessUrl("/customer/customerDashBoard")
        .and().csrf().disable();
		
		 // Configure default success URL based on role
	    http
	        .formLogin()
	            .successHandler((request, response, authentication) -> {
	                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	                for (GrantedAuthority authority : authorities) {
	                    if (authority.getAuthority().equals("ROLE_CUSTOMER")) {
	                        response.sendRedirect("/customer/customerDashBoard");
	                        return;
	                    } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
	                        response.sendRedirect("/admin/adminDashBoard");
	                        return;
	                    }
	                }
	                response.sendRedirect("/home");
	            }); 
	} 
	
}
