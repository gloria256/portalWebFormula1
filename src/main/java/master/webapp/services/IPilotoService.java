package master.webapp.services;

import master.webapp.dto.PilotoDtoIn;
import master.webapp.entidades.Piloto;
import master.webapp.util.ResponseUtil;

public interface IPilotoService {
    Piloto getById(Integer eId);
    ResponseUtil create(PilotoDtoIn ePiloto);
    ResponseUtil update(PilotoDtoIn ePiloto);
}
