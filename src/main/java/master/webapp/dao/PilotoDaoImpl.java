package master.webapp.dao;

import master.webapp.entidades.Piloto;
import master.webapp.repositorios.PilotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PilotoDaoImpl implements IPilotoDao{

    private final PilotoRepositorio repositorio;

    @Autowired
    public PilotoDaoImpl(PilotoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Piloto getById(Integer eId) {
        return repositorio.findById(eId).orElse(null);
    }

    @Override
    public void save(Piloto ePiloto) {
        repositorio.save(ePiloto);
    }
}
