package master.webapp.services;

import master.webapp.dao.IEquipoDao;
import master.webapp.dao.IPilotoDao;
import master.webapp.dto.PilotoDtoIn;
import master.webapp.dto.PilotoDtoOut;
import master.webapp.entidades.Equipo;
import master.webapp.entidades.Piloto;
import master.webapp.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PilotoService implements IPilotoService{

    private final IPilotoDao pilotoDao;
    private final ModelMapper _modelMapper;
    private final IEquipoDao equipoDao;

    @Autowired
    public PilotoService(IPilotoDao pilotoDao, ModelMapper modelMapper, IEquipoDao equipoDao) {
        this.pilotoDao = pilotoDao;
        this._modelMapper = modelMapper;
        this.equipoDao = equipoDao;
    }

    @Override
    public Piloto getById(Integer eId) {
        return pilotoDao.getById(eId);
    }

    @Override
    public ResponseUtil create(PilotoDtoIn ePiloto) {
        ResponseUtil _response = new ResponseUtil();
        ePiloto.setId(null);
        Equipo equipo = equipoDao.getById(1); // SOL TMP
        Piloto data = _modelMapper.map(ePiloto, Piloto.class);
        data.setEquipo(equipo);
        pilotoDao.save(data);

        return _response;
    }

    @Override
    public ResponseUtil update(PilotoDtoIn ePiloto) {
        ResponseUtil _response = new ResponseUtil();
        Piloto db = pilotoDao.getById(ePiloto.getId());
        Equipo equipo = equipoDao.getById(1); // SOL TMP
        if (db != null) {
            Piloto data = _modelMapper.map(ePiloto, Piloto.class);
            if (data.getDataurlb64().isEmpty()) data.setDataurlb64(db.getDataurlb64());
            data.setEquipo(equipo);
            pilotoDao.save(data);
        }
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

    @Override
    public Boolean existSiglas(String eSiglas, Integer eEstado, Optional<Integer> ePilotoId) {
        Integer pilotoId = ePilotoId.orElse(0);
        return pilotoDao.existsSiglasPiloto(eSiglas, eEstado, pilotoId);
    }
}
