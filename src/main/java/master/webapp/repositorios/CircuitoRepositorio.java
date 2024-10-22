package master.webapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import master.webapp.entidades.Circuito;

@Repository
public interface CircuitoRepositorio extends JpaRepository<Circuito,Integer>{

}