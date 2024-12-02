package master.webapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import master.webapp.entidades.Circuito;
import master.webapp.entidades.Coche;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche,Integer>{

}
