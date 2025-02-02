package master.webapp.dao;

import master.webapp.entidades.Piloto;
import master.webapp.repositorios.PilotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Piloto> getAll() {
        return repositorio.findAll();
    }

    @Override
    public List<Piloto> gelAllByEqipoId(Integer eEquipoId) {
        return repositorio.gelAllByEqipoId(eEquipoId);
    }

    @Override
    public Boolean existsDorsalPiloto(Integer eDorsal, Integer eEstado, Integer eEquipoId, Integer ePilotoId) {
        return repositorio.existsByDorsalAndEstadoAndEquipoIdAndNotEqualsPilotoId(eDorsal, eEstado, eEquipoId, ePilotoId);
    }

    @Override
    public Boolean existsSiglasPiloto(String eSiglas, Integer eEstado, Integer ePilotoId) {
        return repositorio.existsBySiglasAndEstadoAndNotEqualsPilotoId(eSiglas, eEstado, ePilotoId);
    }

    @Override
    public void delete(Piloto ePiloto) {
        repositorio.delete(ePiloto);
    }
}
