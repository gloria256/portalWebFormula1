package master.webapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import master.webapp.entidades.Votacion;

@Repository
public interface VotacionRepositorio extends JpaRepository<Votacion,Integer>{

}