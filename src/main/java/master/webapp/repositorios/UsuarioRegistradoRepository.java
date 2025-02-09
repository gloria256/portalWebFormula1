package master.webapp.repositorios;

import master.webapp.entidades.UsuarioRegistrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRegistradoRepository extends JpaRepository<UsuarioRegistrado, Integer> {
    Optional<UsuarioRegistrado> findByUsername(String username);
    Optional<UsuarioRegistrado> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsernameOrEmail(String username, String email);
    @Query("select u from UsuarioRegistrado u where (u.username = ?1 OR u.email = ?2) AND u.estado = ?3")
    Optional<UsuarioRegistrado> findByUsernameOrEmailAfterAndEstado(String username, String email, String estado);
    @Query("select u from UsuarioRegistrado u where u.username = ?1 AND u.estado = ?2")
    Optional<UsuarioRegistrado> findByUsernameAndEstado(String username, String estado);
    @Query("select u from Equipo e join e.responsables u where e.id = ?1 AND u.estado = ?2")
    List<UsuarioRegistrado> findByEquipoId(Integer eEquipoId, String eEstado);
}