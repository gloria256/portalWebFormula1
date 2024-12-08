package master.webapp.services;

import master.webapp.dao.IEquipoDao;
import master.webapp.entidades.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoServiceImpl implements IEquipoService{
    private final IEquipoDao equipoDao;

    @Autowired
    public EquipoServiceImpl(IEquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }

    @Override
    public Equipo getById(Integer eId) {
        return equipoDao.getById(eId);
    }
}
