package master.webapp.services;

import master.webapp.dao.IRolDAO;
import master.webapp.dao.IUsuarioRegistradoDAO;
import master.webapp.dto.UsuarioDtoIn;
import master.webapp.dto.UsuarioDtoOut;
import master.webapp.entidades.Rol;
import master.webapp.entidades.UsuarioRegistrado;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ErrorUtil;
import master.webapp.util.ResponseUtil;
import master.webapp.util.ValidationsUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioRegistradoServiceImpl implements IUsuarioRegistradoService{
    private final IUsuarioRegistradoDAO _dao;
    private final IRolDAO _rolDao;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper _modelMapper;

    @Autowired
    public UsuarioRegistradoServiceImpl(IUsuarioRegistradoDAO _dao, IRolDAO _rolDao, PasswordEncoder passwordEncoder, ModelMapper _modelMapper) {
        this._dao = _dao;
        this._rolDao = _rolDao;
        this.passwordEncoder = passwordEncoder;
        this._modelMapper = _modelMapper;
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
        if (validate(eUsuario, _response, false)) {
            UsuarioRegistrado _user = getUserMapper(eUsuario);
            _dao.delete(_user);
        }
        return _response;
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
}
