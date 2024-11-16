package master.webapp.dao;

import master.webapp.entidades.UsuarioRegistrado;

import java.util.List;

public interface IUsuarioRegistradoDAO {
    List<UsuarioRegistrado> getAll();
    UsuarioRegistrado getById(Integer eId);
}
