package master.webapp.services;

import master.webapp.dao.IPilotoDao;
import master.webapp.dto.PilotoDtoIn;
import master.webapp.dto.PilotoDtoOut;
import master.webapp.dto.UsuarioDtoOut;
import master.webapp.entidades.Piloto;
import master.webapp.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PilotoService implements IPilotoService{

    private final IPilotoDao pilotoDao;
    private final ModelMapper _modelMapper;

    @Autowired
    public PilotoService(IPilotoDao pilotoDao, ModelMapper modelMapper) {
        this.pilotoDao = pilotoDao;
        this._modelMapper = modelMapper;
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

    @Override
    public List<Piloto> getAll() {
        return pilotoDao.getAll();
    }

    @Override
    public List<PilotoDtoOut> getAllByEquipoId(Integer eEquipoId) {
        // Cambiar y tomar el equipoId del usuario logeado
        return pilotoDao.gelAllByEqipoId(eEquipoId)
                .stream()
                .map(el -> {
                    return _modelMapper.map(el, PilotoDtoOut.class);
                })
                .collect(Collectors.toList());
    }
}
