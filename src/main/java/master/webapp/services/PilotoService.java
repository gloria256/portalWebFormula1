package master.webapp.services;

import master.webapp.dao.IEquipoDao;
import master.webapp.dao.IPilotoDao;
import master.webapp.dao.IUsuarioRegistradoDAO;
import master.webapp.dto.PilotoDtoIn;
import master.webapp.dto.PilotoDtoOut;
import master.webapp.entidades.Equipo;
import master.webapp.entidades.Piloto;
import master.webapp.entidades.UsuarioRegistrado;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ErrorUtil;
import master.webapp.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PilotoService implements IPilotoService{

    private final IPilotoDao pilotoDao;
    private final ModelMapper _modelMapper;
    private final IEquipoDao equipoDao;
    private final IUsuarioRegistradoDAO usuarioRegistradoDao;
    private Boolean isRolEquipo = false;
    private Integer UsuarioLoginId = 0;

    @Autowired
    public PilotoService(IPilotoDao pilotoDao, ModelMapper modelMapper, IEquipoDao equipoDao, IUsuarioRegistradoDAO usuarioRegistradoDao) {
        this.pilotoDao = pilotoDao;
        this._modelMapper = modelMapper;
        this.equipoDao = equipoDao;
        this.usuarioRegistradoDao = usuarioRegistradoDao;
    }

    @Override
    public Piloto getById(Integer eId) {
        return pilotoDao.getById(eId);
    }

    @Override
    public ResponseUtil create(PilotoDtoIn ePiloto) {
        ResponseUtil _response = new ResponseUtil();
        ePiloto.setId(null);
        // Get EquipoLogin
        int equipoId = getEquipoIdByUserLogin(0);
        Equipo equipo = equipoDao.getById(equipoId); // SOL TMP
        Piloto data = _modelMapper.map(ePiloto, Piloto.class);
        data.setEquipo(equipo);
        pilotoDao.save(data);

        return _response;
    }

    @Override
    public ResponseUtil update(PilotoDtoIn ePiloto) {
        ResponseUtil _response = new ResponseUtil();
        Piloto db = pilotoDao.getById(ePiloto.getId());
        int equipoId = getEquipoIdByUserLogin(0);
        Equipo equipo = equipoDao.getById(equipoId);
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
        int equipoId = getEquipoIdByUserLogin(0);
        return pilotoDao.gelAllByEqipoId(equipoId)
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

    @Override
    public ResponseUtil delete(Integer eId) {
        ResponseUtil _response = new ResponseUtil();
        Piloto db = pilotoDao.getById(eId);
        try{
            if (db != null) {
                pilotoDao.delete(db);
            } else {
                _response.setStatus(ConstantsUtil.FAILURE);
                _response.getErrors().add(new ErrorUtil("object", "Piloto no encontrado"));
            }
        }catch(Exception ex){
            _response.setStatus(ConstantsUtil.FAILURE);
            _response.getErrors().add(new ErrorUtil("object", "Piloto relacionado con otras entidades"));
        }
        return _response;
    }

    private Integer getEquipoIdByUserLogin(Integer eId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            UsuarioRegistrado userLogin = usuarioRegistradoDao.getByUsernameAndEstado(username, "Aprobado");
            if(userLogin != null){
                UsuarioLoginId = userLogin.getId();
                // Verifica si tiene rol Equipo
                userLogin.getRoles().forEach(role -> {
                    if(role.getName().compareToIgnoreCase("Equipo") == 0){
                        isRolEquipo = true;
                    }
                });
                // Si el usuario tiene rol Equipo, se busca si tiene rol asignado
                if(isRolEquipo){
                    Equipo equipoUserLogin = equipoDao.getByResponsableId(userLogin.getId());
                    if(equipoUserLogin != null){
                        eId = equipoUserLogin.getId() != null ? equipoUserLogin.getId() : 0;
                    }
                }
            }
        } catch (Exception e) {
            UsuarioLoginId = 0;
            isRolEquipo = false;
        }
        return eId != null ? eId : 0; // provicional se saca de token -> user -> equipo
    }
}
