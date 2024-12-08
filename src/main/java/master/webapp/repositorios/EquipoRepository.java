package master.webapp.repositorios;

import master.webapp.entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}