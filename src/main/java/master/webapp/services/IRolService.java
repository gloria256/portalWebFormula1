package master.webapp.services;

import master.webapp.entidades.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    List<Rol> getAll();
    Rol getById(Integer eId);
}
