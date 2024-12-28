package master.webapp.dao;

import master.webapp.entidades.UsuarioRegistrado;
import master.webapp.repositorios.UsuarioRegistradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRegistradoDAOImpl implements IUsuarioRegistradoDAO {
    @Autowired
    UsuarioRegistradoRepository _repository;

    @Override
    public List<UsuarioRegistrado> getAll() {
        return _repository.findAll();
    }

    @Override
    public UsuarioRegistrado getById(Integer eId) {
        return _repository.findById(eId).orElse(null);
    }

    @Override
    public void save(UsuarioRegistrado eUsuario) {
        _repository.save(eUsuario);
    }

    @Override
    public boolean existsByUsername(String eUsername) {
        return _repository.existsByUsername(eUsername);
    }

    @Override
    public boolean existsByEmail(String eEmail) {
        return _repository.existsByEmail(eEmail);
    }

    @Override
    public UsuarioRegistrado getByUsernameAndEstado(String eUsername, String eEstado) {
        return _repository.findByUsernameAndEstado(eUsername, eEstado).orElse(null);
    }
}
