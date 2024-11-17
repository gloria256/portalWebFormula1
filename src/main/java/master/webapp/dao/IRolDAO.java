package master.webapp.dao;

import master.webapp.entidades.Rol;

import java.util.List;

public interface IRolDAO {
    List<Rol> getAll();
    Rol getById(Integer eId);
}
