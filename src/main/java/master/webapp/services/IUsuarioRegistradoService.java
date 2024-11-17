package master.webapp.services;

import master.webapp.dto.UsuarioDtoIn;
import master.webapp.dto.UsuarioDtoOut;
import master.webapp.util.ResponseUtil;

import java.util.List;

public interface IUsuarioRegistradoService {
    List<UsuarioDtoOut> getAll();
    UsuarioDtoOut getById(Integer eId);
    ResponseUtil create(UsuarioDtoIn eUsuario);
    ResponseUtil update(UsuarioDtoIn eUsuario);
}
