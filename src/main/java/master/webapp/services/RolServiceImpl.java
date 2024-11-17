package master.webapp.services;

import master.webapp.dao.IRolDAO;
import master.webapp.entidades.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService{
    private final IRolDAO _rolDao;

    @Autowired
    public RolServiceImpl(IRolDAO rolDao) {
        _rolDao = rolDao;
    }

    @Override
    public List<Rol> getAll() {
        return _rolDao.getAll();
    }

    @Override
    public Rol getById(Integer eId) {
        return _rolDao.getById(eId);
    }
}
