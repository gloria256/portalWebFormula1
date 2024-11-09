package master.webapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import master.webapp.entidades.Piloto;

@Repository
public interface PilotoRepositorio extends JpaRepository<Piloto,Integer>{

}
