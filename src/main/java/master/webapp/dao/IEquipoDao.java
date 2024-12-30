package master.webapp.dao;

import master.webapp.entidades.Equipo;

import java.util.List;
import java.util.Optional;

public interface IEquipoDao {
    Equipo getById(Integer eId);
    List<Equipo> getAll();
    void save(Equipo eEquipo);
    Equipo getByResponsableId(Integer eResponsableId);
}
