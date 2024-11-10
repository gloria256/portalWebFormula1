package master.webapp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import master.webapp.entidades.VotacionEmitida;

@Repository
public interface VotacionEmitidaRepositorio extends JpaRepository<VotacionEmitida,Integer>{
	 List<VotacionEmitida> findByVotacionId(Integer votacionId);
	 List<VotacionEmitida> findByVotacionIdAndPilotoId(Integer votacionId, Integer pilotoId);
	 List<VotacionEmitida> findByEmail(String email);
	 boolean existsByEmail(String email);
}
