package master.webapp.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    @SuppressWarnings({ "deprecation", "removal" })
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable() // Asegúrate de que esto esté habilitado
            .authorizeRequests()
            .requestMatchers(HttpMethod.GET,"/portalWebFormula1/**").permitAll() //Rutas permitidas
            .requestMatchers(HttpMethod.POST, "/portalWebFormula1/**").authenticated() // Requiere autenticación para POST
            .anyRequest().authenticated() // Resto requiere autenticación
            .and()
            .httpBasic();

        return http.build();
    }
    
    /*
     * .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout().permitAll();
     */

	@Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    	
    	AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
            .inMemoryAuthentication()
            .withUser("usuario").password("{noop}contraseña").roles("USER") // {noop} indica que no se usa ningun codificador de contraseña. en prod se debe colocar
        	.and()
        	.withUser("master").password("{noop}Master%2024").roles("ADMIN");
        return authenticationManagerBuilder.build();
    }
}
