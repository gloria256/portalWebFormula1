package master.webapp.dao;

import master.webapp.entidades.Rol;
import master.webapp.repositorios.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolDAOImpl implements IRolDAO{
    @Autowired
    RolRepository _repository;

    @Override
    public List<Rol> getAll() {
        return _repository.findAll();
    }

    @Override
    public Rol getById(Integer eId) {
        return _repository.findById(eId).orElse(null);
    }
}
