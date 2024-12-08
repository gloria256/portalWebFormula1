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
    @Query("select case when COUNT(p) > 0 then true else false end from Piloto p join p.equipo e where e.id = ?3 and p.dorsal = ?1 and p.estado = ?2 and p.id <> ?4")
    Boolean existsByDorsalAndEstadoAndEquipoIdAndNotEqualsPilotoId(Integer eDorsal, Integer eEstado, Integer eEquipoId, Integer ePilotoId);
    @Query("select case when COUNT(p) > 0 then true else false end from Piloto p where p.siglas = ?1 and p.estado = ?2 and p.id <> ?3")
    Boolean existsBySiglasAndEstadoAndNotEqualsPilotoId(String eSiglas, Integer eEstado, Integer ePilotoId);
}
