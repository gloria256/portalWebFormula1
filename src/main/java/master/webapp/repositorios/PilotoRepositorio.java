package master.webapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import master.webapp.entidades.Piloto;

import java.util.List;

@Repository
public interface PilotoRepositorio extends JpaRepository<Piloto,Integer>{
    @Query("select p from Piloto p join p.equipo e where e.id = ?1")
    List<Piloto> gelAllByEqipoId(Integer eEquipoId);
}
