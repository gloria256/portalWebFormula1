package master.webapp.repositorios;

import master.webapp.entidades.UsuarioRegistrado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRegistradoRepository extends JpaRepository<UsuarioRegistrado, Integer> {
    Optional<UsuarioRegistrado> findByUsername(String username);
    Optional<UsuarioRegistrado> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsernameOrEmail(String username, String email);
    Optional<UsuarioRegistrado> findByUsernameOrEmailAfterAndEstado(String username, String email, String estado);
    Optional<UsuarioRegistrado> findByUsernameAndEstado(String username, String estado);
}