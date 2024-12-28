package master.webapp.services;

import master.webapp.dao.IEquipoDao;
import master.webapp.dao.IUsuarioRegistradoDAO;
import master.webapp.entidades.Equipo;
import master.webapp.entidades.UsuarioRegistrado;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ErrorUtil;
import master.webapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements IEquipoService{
    private final IEquipoDao equipoDao;
    private final IUsuarioRegistradoDAO usuarioRegistradoDao;
    private Boolean isRolEquipo = false;
    private Integer UsuarioLoginId = 0;

    @Autowired
    public EquipoServiceImpl(IEquipoDao equipoDao, IUsuarioRegistradoDAO usuarioRegistradoDao) {
        this.equipoDao = equipoDao;
        this.usuarioRegistradoDao = usuarioRegistradoDao;
    }

    @Override
    public Equipo getById(Integer eId) {
        eId = getEquipoIdByUserLogin(eId);
        return equipoDao.getById(eId);
    }

    @Override
    public List<Equipo> getAll() {
        int id = getEquipoIdByUserLogin(0);
        if (isRolEquipo) return List.of(equipoDao.getById(id));
        return equipoDao.getAll();
    }

    @Override
    public ResponseUtil create(Equipo eEquipo) {
        ResponseUtil _response = new ResponseUtil();
        eEquipo.setId(null);
        validate(eEquipo, _response, true);
        if(_response.getStatus().compareTo(ConstantsUtil.SUCCESS) == 0)
        {
            equipoDao.save(eEquipo);
        }
        return _response;
    }

    @Override
    public ResponseUtil update(Equipo eEquipo) {
        ResponseUtil _response = new ResponseUtil();
        validate(eEquipo, _response, false);
        if(_response.getStatus().compareTo(ConstantsUtil.SUCCESS) == 0)
        {
            equipoDao.save(eEquipo);
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

    private void validate(Equipo eEquipo, ResponseUtil eResponse, boolean eIsNew) {
        Equipo equipo = equipoDao.getById(eEquipo.getId() != null ? eEquipo.getId() : 0);
        int equipoIdByUserLogin = getEquipoIdByUserLogin(eEquipo.getId() != null ? eEquipo.getId() : 0);
        if (!eIsNew && equipo == null) {
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("object");
            error.setMessage("No se puede actualizar el equipo");
            eResponse.getErrors().add(error);
            return;
        } else if (isRolEquipo && eIsNew && equipoIdByUserLogin > 0) {
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("object");
            error.setMessage("Ya existe un equipo con ese rol!!");
            eResponse.getErrors().add(error);
            return;
        } else if(equipoIdByUserLogin != (eEquipo.getId() != null ? eEquipo.getId() : 0)) {
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("object");
            error.setMessage("No se puede actualizar informacion de otro equipo!!");
            eResponse.getErrors().add(error);
            return;
        }

        // Solo para usuario con rol de equipo se asocia por primera vez, cuando el equipo es nuevo
        if(isRolEquipo && eIsNew) {
            UsuarioRegistrado usuarioRegistrado = usuarioRegistradoDao.getById(UsuarioLoginId);
            if (usuarioRegistrado == null) {
                eResponse.setStatus(ConstantsUtil.FAILURE);
                ErrorUtil error = new ErrorUtil();
                error.setField("object");
                error.setMessage("No se puede asociar responsable a equipo!!");
                eResponse.getErrors().add(error);
            } else eEquipo.getResponsables().add(usuarioRegistrado);
        } else if(equipo != null) {
            eEquipo.getResponsables().clear();
            eEquipo.getResponsables().addAll(equipo.getResponsables());
        }

        if(eEquipo.getNombre() == null || eEquipo.getNombre().isEmpty()) {
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("nombre");
            error.setMessage("El nombre no puede ser vacio");
            eResponse.getErrors().add(error);
        }

        if(eEquipo.getLogo() == null || eEquipo.getLogo().isEmpty()) {
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("logo");
            error.setMessage("El logo no puede ser vacio");
            eResponse.getErrors().add(error);
        }

        if(eEquipo.getTwitter() == null || eEquipo.getTwitter().isEmpty()) {
            eResponse.setStatus(ConstantsUtil.FAILURE);
            ErrorUtil error = new ErrorUtil();
            error.setField("logo");
            error.setMessage("El twitter no puede ser vacio");
            eResponse.getErrors().add(error);
        }
    }
}