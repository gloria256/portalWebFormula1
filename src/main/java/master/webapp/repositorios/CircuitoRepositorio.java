package master.webapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import master.webapp.entidades.Circuito;

import java.util.Optional;

@Repository
public interface CircuitoRepositorio extends JpaRepository<Circuito,Integer>{

    Optional<Circuito> findByNombre(String nombreCircuito);
}