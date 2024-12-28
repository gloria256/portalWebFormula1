package master.webapp.seguridad;

import master.webapp.entidades.Rol;
import master.webapp.entidades.UsuarioRegistrado;
import master.webapp.repositorios.UsuarioRegistradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityImpl implements UserDetailsService {
    private UsuarioRegistradoRepository userRepository;

    @Autowired
    public void CustomUserDetailsService(UsuarioRegistradoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioRegistrado user = userRepository.findByUsernameOrEmailAfterAndEstado(username,username,"Aprobado").orElseThrow(() -> new UsernameNotFoundException("Username or Email not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
