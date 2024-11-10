package master.webapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import master.webapp.entidades.Votacion;


@Repository
public interface VotacionRepositorio extends JpaRepository<Votacion,Integer>{
	@Query("SELECT v.listaPilotos FROM Votacion v WHERE v.id = :id")
    String findListaPilotosById(@Param("id") Integer id);
}