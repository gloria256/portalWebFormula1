package master.webapp.services;

import master.webapp.dao.IEquipoDao;
import master.webapp.dao.IRolDAO;
import master.webapp.dao.IUsuarioRegistradoDAO;
import master.webapp.dto.UsuarioDtoIn;
import master.webapp.dto.UsuarioDtoOut;
import master.webapp.entidades.Equipo;
import master.webapp.entidades.Rol;
import master.webapp.entidades.UsuarioRegistrado;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ErrorUtil;
import master.webapp.util.ResponseUtil;
import master.webapp.util.ValidationsUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class UsuarioRegistradoServiceImpl implements IUsuarioRegistradoService{
    private final IUsuarioRegistradoDAO _dao;
    private final IRolDAO _rolDao;
    private final IEquipoDao equipoDao;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper _modelMapper;
    private Boolean isRolEquipo = false;
    private int idRolEquipo = 0;
    private int idUsuarioLogin = 0;

    @Autowired
    public UsuarioRegistradoServiceImpl(IUsuarioRegistradoDAO _dao, IRolDAO _rolDao, PasswordEncoder passwordEncoder, ModelMapper _modelMapper,
                                        IEquipoDao equipoDao) {
        this._dao = _dao;
        this._rolDao = _rolDao;
        this.passwordEncoder = passwordEncoder;
        this._modelMapper = _modelMapper;
        this.equipoDao = equipoDao;
    }

    @Override
    public List<UsuarioDtoOut> getAll() {
        return _dao.getAll()
                .stream()
                .map(user -> {
                    UsuarioDtoOut dto = _modelMapper.map(user, UsuarioDtoOut.class);
                    dto.setRol(!user.getRoles().isEmpty() ? user.getRoles().get(0).getName() : "");
                    dto.setIdRol(!user.getRoles().isEmpty() ? user.getRoles().get(0).getId() : 0);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDtoOut getById(Integer eId) {
        UsuarioRegistrado user = _dao.getById(eId);
        UsuarioDtoOut dto = null;
        if (user != null) {
            dto = _modelMapper.map(user, UsuarioDtoOut.class);
            dto.setIdRol(!user.getRoles().isEmpty() ? user.getRoles().get(0).getId() : 0);
        }
        return dto;
    }

    @Override
    public ResponseUtil create(UsuarioDtoIn eUsuario) {
        ResponseUtil _response = new ResponseUtil();
        eUsuario.setId(null);
        if (validate(eUsuario, _response, true)) {
            UsuarioRegistrado user = getUserMapper(eUsuario);
            _dao.save(user);
        }
        return _response;
    }

    @Override
    public ResponseUtil update(UsuarioDtoIn eUsuario) {
        ResponseUtil _response = new ResponseUtil();
        if (validate(eUsuario, _response, false)) {
            UsuarioRegistrado _user = getUserMapper(eUsuario);
            _dao.save(_user);
        }
        return _response;
    }

    @Override
    public ResponseUtil delete(UsuarioDtoIn eUsuario) {
        ResponseUtil _response = new ResponseUtil();
        AtomicBoolean _tieneRolEquipo = new AtomicBoolean(false);
        if (validate(eUsuario, _response, false)) {
            UsuarioRegistrado _user = getUserMapper(eUsuario);
            // Si el usuario tiene rol de EQUIPO, borramos como responsable
            UsuarioRegistrado user = _dao.getById(_user.getId());
            user.getRoles().forEach(role -> {
                if (role.getName().compareToIgnoreCase("Equipo")==0) {
                    _tieneRolEquipo.set(true);
                }
            });
            if (_tieneRolEquipo.get()) {
                Equipo miEquipo = equipoDao.getByResponsableId(user.getId());
                if (miEquipo != null) {
                    miEquipo.getResponsables().remove(user);
                    equipoDao.save(miEquipo);
                }
            }
            _dao.delete(_user);
        }
        return _response;
    }

    @Override
    public ResponseUtil addResponsableEquipo(UsuarioDtoIn eUsuario) {
        ResponseUtil _response = new ResponseUtil();
        int equipoIdByUserLogin = getEquipoIdByUserLogin();
        eUsuario.setEstado(ConstantsUtil.STATE_APPROVED);
        eUsuario.setIdRol(idRolEquipo);
        if (equipoIdByUserLogin > 0) {
            eUsuario.setId(null);
            if (validate(eUsuario, _response, true)) {
                UsuarioRegistrado user = getUserMapper(eUsuario);
                UsuarioRegistrado userDB = _dao.save(user);
                Equipo equipo = equipoDao.getById(equipoIdByUserLogin);
                equipo.getResponsables().add(userDB);
                equipoDao.save(equipo);
            }
        } else {
            _response.setStatus(ConstantsUtil.FAILURE);
            _response.getErrors().add(new ErrorUtil("equipo", "El equipo no puede ser nulo"));
        }
        return _response;
    }

    @Override
    public ResponseUtil updateResponsableEquipo(UsuarioDtoIn eUsuario) {
        ResponseUtil _response = new ResponseUtil();
        int equipoIdByUserLogin = getEquipoIdByUserLogin();
        eUsuario.setEstado(ConstantsUtil.STATE_APPROVED);
        eUsuario.setIdRol(idRolEquipo);
        if (equipoIdByUserLogin > 0) {
            if (validate(eUsuario, _response, false)) {
                UsuarioRegistrado user = getUserMapper(eUsuario);
                Equipo equipo = equipoDao.getById(equipoIdByUserLogin);
                UsuarioRegistrado userResp = equipo.getResponsables().stream().filter(data -> data.getId().intValue() == eUsuario.getId().intValue()).findFirst().orElse(null);
                if (userResp != null) {
                    _dao.save(user);
                } else {
                    _response.setStatus(ConstantsUtil.FAILURE);
                    _response.getErrors().add(new ErrorUtil("responsable", "Responsable no pertenece a su equipo"));
                }
            }
        } else {
            _response.setStatus(ConstantsUtil.FAILURE);
            _response.getErrors().add(new ErrorUtil("equipo", "El equipo no puede ser nulo"));
        }
        return _response;
    }

    @Override
    public ResponseUtil deleteResponsableEquipo(int eUsuarioId) {
        ResponseUtil _response = new ResponseUtil();
        UsuarioRegistrado user = _dao.getById(eUsuarioId);
        int equipoIdByUserLogin = getEquipoIdByUserLogin();
        if (user != null) {
            Equipo equipo = equipoDao.getById(equipoIdByUserLogin);
            if (equipo != null) {
                UsuarioRegistrado userResp = equipo.getResponsables().stream().filter(data -> data.getId().intValue() == user.getId().intValue()).findFirst().orElse(null);
                if (userResp != null) {
                    try{
                        equipo.getResponsables().remove(user); // removemos el responsable del equipo
                        equipoDao.save(equipo); // actualizamos
                        _dao.delete(user); // elimina el usuario
                    }catch(Exception err) {
                        _response.setStatus(ConstantsUtil.FAILURE);
                        _response.getErrors().add(new ErrorUtil("object", "Error al eliminar usuario"));
                    }
                }else {
                    _response.setStatus(ConstantsUtil.FAILURE);
                    _response.getErrors().add(new ErrorUtil("responsable", "Responsable no pertenece a su equipo"));
                }
            }else {
                _response.setStatus(ConstantsUtil.FAILURE);
                _response.getErrors().add(new ErrorUtil("equipo", "El equipo no puede ser nulo"));
            }
        }else {
            _response.setStatus(ConstantsUtil.FAILURE);
            _response.getErrors().add(new ErrorUtil("responsable", "El responsable no existe"));
        }
        return _response;
    }

    @Override
    public List<UsuarioDtoOut> findByEquipoId() {
        int equipoIdByUserLogin = getEquipoIdByUserLogin();
        return _dao.findByEquipoId(equipoIdByUserLogin, ConstantsUtil.STATE_APPROVED)
                .stream()
                .filter(u -> u.getId() != idUsuarioLogin)
                .map(user -> {
                    UsuarioDtoOut dto = _modelMapper.map(user, UsuarioDtoOut.class);
                    dto.setRol(!user.getRoles().isEmpty() ? user.getRoles().get(0).getName() : "");
                    dto.setIdRol(!user.getRoles().isEmpty() ? user.getRoles().get(0).getId() : 0);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private UsuarioRegistrado getUserMapper(UsuarioDtoIn eUsuario) {
        UsuarioRegistrado user = _modelMapper.map(eUsuario, UsuarioRegistrado.class);
        Rol rol = _rolDao.getById(eUsuario.getIdRol());
        user.getRoles().clear();
        user.getRoles().add(rol);
        user.setRol(rol.getName());
        return user;
    }

    private boolean validate(UsuarioDtoIn eUsuario, ResponseUtil eResponse, boolean eIsNew) {
        boolean _result = true;
        UsuarioRegistrado user = _dao.getById(eUsuario.getId() != null ? eUsuario.getId() : 0);

        if (!eIsNew && user == null) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("object");
            error.setMessage("No se puede actualizar el usuario");
            eResponse.getErrors().add(error);
            return _result;
        }

        if(eUsuario.getNombre() == null || eUsuario.getNombre().isEmpty()) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("nombre");
            error.setMessage("El nombre no puede ser vacio");
            eResponse.getErrors().add(error);
        }

        if(eUsuario.getUsername() == null || eUsuario.getUsername().isEmpty()) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("username");
            error.setMessage("El username no puede ser vacio");
            eResponse.getErrors().add(error);
        }else if(user != null) {
            if (user.getUsername().compareToIgnoreCase(eUsuario.getUsername()) != 0 &&
                    _dao.existsByUsername(eUsuario.getUsername())
            ) {
                _result = false;
                eResponse.setStatus(ConstantsUtil.FAILURE);
                ErrorUtil error = new ErrorUtil();
                error.setField("username");
                error.setMessage("Ya existe un usuario con ese username");
                eResponse.getErrors().add(error);
            }
        }else if(_dao.existsByUsername(eUsuario.getUsername())) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("username");
            error.setMessage("Ya existe un usuario con ese username");
            eResponse.getErrors().add(error);
        }

        if(!ValidationsUtil.emailIsValid(eUsuario.getEmail())) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("email");
            error.setMessage("El email no es valido");
            eResponse.getErrors().add(error);
        }else if(user != null) {
            if (user.getEmail().compareToIgnoreCase(eUsuario.getEmail()) != 0 &&
                    _dao.existsByEmail(eUsuario.getEmail())
            ) {
                _result = false;
                eResponse.setStatus(ConstantsUtil.FAILURE);
                ErrorUtil error = new ErrorUtil();
                error.setField("email");
                error.setMessage("Ya existe un usuario con ese email");
                eResponse.getErrors().add(error);
            }
        }else if(_dao.existsByEmail(eUsuario.getEmail())) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("email");
            error.setMessage("Ya existe un usuario con ese email");
            eResponse.getErrors().add(error);
        }

        if(user == null && (eUsuario.getPassword() == null || eUsuario.getPassword().isEmpty())) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("password");
            error.setMessage("El password no puede ser vacio");
            eResponse.getErrors().add(error);
        }else if(user != null &&
                (eUsuario.getPassword() == null || eUsuario.getPassword().isEmpty())){
            eUsuario.setPassword(user.getPassword());
        }else{
            eUsuario.setPassword(passwordEncoder.encode((eUsuario.getPassword())));
        }

        if(!ConstantsUtil.states.contains(eUsuario.getEstado() == null ? "" : eUsuario.getEstado())) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("estado");
            error.setMessage("El estado no es valido");
            eResponse.getErrors().add(error);
        }

        Rol rol = _rolDao.getById(eUsuario.getIdRol() != null ? eUsuario.getIdRol() : 0);
        if (rol == null) {
            _result = false;
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("rol");
            error.setMessage("El rol no es valido");
            eResponse.getErrors().add(error);
        }
        return _result;
    }

    private Integer getEquipoIdByUserLogin() {
        int equipoId = 0;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            UsuarioRegistrado userLogin = _dao.getByUsernameAndEstado(username, "Aprobado");
            if(userLogin != null){
                // Verifica si tiene rol Equipo
                idUsuarioLogin = userLogin.getId();
                userLogin.getRoles().forEach(role -> {
                    if(role.getName().compareToIgnoreCase("Equipo") == 0){
                        isRolEquipo = true;
                        idRolEquipo = role.getId();
                    }
                });
                // Si el usuario tiene rol Equipo, se busca si tiene rol asignado
                if(isRolEquipo){
                    Equipo equipoUserLogin = equipoDao.getByResponsableId(userLogin.getId());
                    if(equipoUserLogin != null){
                        equipoId = equipoUserLogin.getId() != null ? equipoUserLogin.getId() : 0;
                    }
                }
            }
        } catch (Exception e) {
            isRolEquipo = false;
        }
        return equipoId;
    }
}
