package master.webapp.controlador;

import master.webapp.dto.LoginDtoIn;
import master.webapp.dto.LoginDtoOut;
import master.webapp.dto.RegisterDtoIn;
import master.webapp.entidades.Rol;
import master.webapp.entidades.UsuarioRegistrado;
import master.webapp.repositorios.RolRepository;
import master.webapp.repositorios.UsuarioRegistradoRepository;
import master.webapp.seguridad.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UsuarioRegistradoRepository userRepository;
    private RolRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsuarioRegistradoRepository userRepository,
                          RolRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDtoOut> login(@RequestBody LoginDtoIn loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new LoginDtoOut(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDtoIn registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UsuarioRegistrado user = new UsuarioRegistrado();
        user.setNombre(registerDto.getUsername());
        user.setEmail(registerDto.getUsername() + "@gmail.com");
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Rol roles = roleRepository.findByName("Administrador").orElse(null);
        if (roles != null) {
            user.setRoles(Collections.singletonList(roles));
            user.setRol(roles.getName());
            userRepository.save(user);

            return new ResponseEntity<>("User registered success!", HttpStatus.OK);
        }


        return new ResponseEntity<>("Error!", HttpStatus.FAILED_DEPENDENCY);
    }
}