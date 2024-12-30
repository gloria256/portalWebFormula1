package master.webapp.seguridad;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {
    /*
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
    }*/

    private JwtAuthEntryPoint authEntryPoint;
    private UserSecurityImpl userDetailsService;

    @Autowired
    public void SecurityConfig(UserSecurityImpl userDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/roles/**").permitAll()
                .requestMatchers("/api/usuarios/**").permitAll()
                .requestMatchers("/api/equipos/**").hasAnyAuthority("Administrador","Equipo")
                .requestMatchers("/portalWebFormula1/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
