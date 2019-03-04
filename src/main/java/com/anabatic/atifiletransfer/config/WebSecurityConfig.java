package com.anabatic.atifiletransfer.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.anabatic.atifiletransfer.component.CustomSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
	private static final String SQL_LOGIN = "SELECT username, password, user_status FROM user WHERE username = ?";
	private static final String SQL_PERMISSION = "SELECT u.username, r.role_name FROM user u JOIN role r ON u.role_role_id = r.role_id WHERE u.username = ?";
	
	@Autowired
    CustomSuccessHandler customSuccessHandler;
 
	@Autowired
	private DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/static/**", "/assets/**").permitAll()
                .antMatchers("/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(customSuccessHandler)
                .and()
            .logout()
                .permitAll()
                .and()
            .csrf().disable();
    }
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    		.jdbcAuthentication()
    		.dataSource(dataSource)
    		.usersByUsernameQuery(SQL_LOGIN).passwordEncoder(passwordEncoder())
    		.authoritiesByUsernameQuery(SQL_PERMISSION);
    }
    
    protected BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
