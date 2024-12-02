package master.webapp.services;

import master.webapp.dto.PilotoDtoIn;
import master.webapp.dto.PilotoDtoOut;
import master.webapp.entidades.Piloto;
import master.webapp.util.ResponseUtil;

import java.util.List;

public interface IPilotoService {
    Piloto getById(Integer eId);
    ResponseUtil create(PilotoDtoIn ePiloto);
    ResponseUtil update(PilotoDtoIn ePiloto);
    List<Piloto> getAll();
    List<PilotoDtoOut> getAllByEquipoId(Integer eEquipoId);
}
