package master.webapp.dao;

import master.webapp.entidades.Piloto;

import java.util.List;

public interface IPilotoDao {
    Piloto getById(Integer eId);
    void save(Piloto ePiloto);
    List<Piloto> getAll();
    List<Piloto> gelAllByEqipoId(Integer eEquipoId);
    Boolean existsDorsalPiloto(Integer eDorsal, Integer eEstado, Integer eEquipoId, Integer ePilotoId);
    Boolean existsSiglasPiloto(String eSiglas, Integer eEstado, Integer ePilotoId);
}