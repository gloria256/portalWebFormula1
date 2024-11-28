package master.webapp.dao;

import master.webapp.entidades.Piloto;
import master.webapp.entidades.UsuarioRegistrado;

public interface IPilotoDao {
    Piloto getById(Integer eId);
    void save(Piloto ePiloto);
}