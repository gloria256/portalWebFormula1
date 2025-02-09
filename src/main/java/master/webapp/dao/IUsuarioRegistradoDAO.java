package master.webapp.dao;

import master.webapp.entidades.UsuarioRegistrado;

import java.util.List;


public interface IUsuarioRegistradoDAO {
    List<UsuarioRegistrado> getAll();
    UsuarioRegistrado getById(Integer eId);
    UsuarioRegistrado save(UsuarioRegistrado eUsuario);
    void delete(UsuarioRegistrado eUsuario);
    boolean existsByUsername(String eUsername);
    boolean existsByEmail(String eEmail);
    UsuarioRegistrado getByUsernameAndEstado(String eUsername, String eEstado);
    List<UsuarioRegistrado> findByEquipoId(Integer eEquipoId, String eEstado);
}
