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
}
