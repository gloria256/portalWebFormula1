package master.webapp.services;

import master.webapp.entidades.Equipo;
import master.webapp.util.ResponseUtil;

import java.util.List;

public interface IEquipoService {
    Equipo getById(Integer eId);
    List<Equipo> getAll();
    ResponseUtil create(Equipo eEquipo);
    ResponseUtil update(Equipo eEquipo);
}
