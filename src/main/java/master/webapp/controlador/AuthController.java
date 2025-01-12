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
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRegistradoRepository userRepository;
    private final RolRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsuarioRegistradoRepository userRepository,
            RolRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<LoginDtoOut> login(@RequestBody LoginDtoIn loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        Optional<UsuarioRegistrado> user = userRepository.findByUsername(loginDto.getUsername());
        if (user.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new LoginDtoOut(token, user), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDtoIn registerDto) {

        String[] dataCorreo = registerDto.getEmail().split("@");
        if (dataCorreo.length == 2) {
            registerDto.setUsername(dataCorreo[0]);
        } else
            registerDto.setUsername("");

        if (userRepository.existsByUsernameOrEmail(registerDto.getUsername(), registerDto.getUsername())) {
            return new ResponseEntity<>("Username or Email ya existe!", HttpStatus.BAD_REQUEST);
        }

        UsuarioRegistrado user = new UsuarioRegistrado();
        user.setEstado("Pendiente");
        user.setNombre(registerDto.getNombre());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Rol roles = roleRepository.findByName("Invitado").orElse(null);

        if (roles != null) {
            user.setRoles(Collections.singletonList(roles));
            user.setRol(roles.getName());
            userRepository.save(user);

            return new ResponseEntity<>("User registered success!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Error!", HttpStatus.FAILED_DEPENDENCY);
    }
}