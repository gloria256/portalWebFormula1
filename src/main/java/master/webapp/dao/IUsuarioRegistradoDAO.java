package master.webapp.dao;

import master.webapp.entidades.UsuarioRegistrado;

import java.util.List;


public interface IUsuarioRegistradoDAO {
    List<UsuarioRegistrado> getAll();
    UsuarioRegistrado getById(Integer eId);
    void save(UsuarioRegistrado eUsuario);
    boolean existsByUsername(String eUsername);
    boolean existsByEmail(String eEmail);
}
