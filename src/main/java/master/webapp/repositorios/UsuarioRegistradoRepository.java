package master.webapp.repositorios;

import master.webapp.entidades.UsuarioRegistrado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRegistradoRepository extends JpaRepository<UsuarioRegistrado, Integer> {
    Optional<UsuarioRegistrado> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByUsernameAndPassword(String username, String password);
}