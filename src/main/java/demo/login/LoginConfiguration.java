package demo.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //Necesario para que funcione la anotación en el servicio oldman 
public class LoginConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Desactiva el método por defecto
		http.csrf().disable()
		    //Agrega el método de filtrado que codificamos nosotros 
			.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/greetings").permitAll()
			.antMatchers(HttpMethod.GET, "/user").permitAll()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(HttpMethod.GET, "/viaje/getAll").permitAll()
			.antMatchers(HttpMethod.GET, "/main.js").permitAll()
			//.antMatchers(HttpMethod.GET, "/oldman").hasAuthority("LINK") // Esta línea es otra manera de agregar requerimientos de logeo.
			.anyRequest().authenticated();
	}
}
