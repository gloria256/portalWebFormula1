package master.webapp.services;

import master.webapp.dao.IPilotoDao;
import master.webapp.dto.PilotoDtoIn;
import master.webapp.entidades.Piloto;
import master.webapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PilotoService implements IPilotoService{

    private final IPilotoDao pilotoDao;

    @Autowired
    public PilotoService(IPilotoDao pilotoDao) {
        this.pilotoDao = pilotoDao;
    }

    @Override
    public Piloto getById(Integer eId) {
        return pilotoDao.getById(eId);
    }

    @Override
    public ResponseUtil create(PilotoDtoIn ePiloto) {
        ResponseUtil _response = new ResponseUtil();
        pilotoDao.save(new Piloto());
        return _response;
    }

    @Override
    public ResponseUtil update(PilotoDtoIn ePiloto) {
        ResponseUtil _response = new ResponseUtil();
        pilotoDao.save(new Piloto());
        return _response;
    }
}
