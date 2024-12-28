package master.webapp.repositorios;

import master.webapp.entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    @Query("SELECT eq FROM Equipo eq JOIN eq.responsables resp WHERE resp.id = ?1")
    Optional<Equipo> getByResponsableId(Integer eResponsableId);
}