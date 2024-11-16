package master.webapp.services;

import master.webapp.dto.UsuarioDtoOut;

import java.util.List;

public interface IUsuarioRegistradoService {
    List<UsuarioDtoOut> getAll();
    UsuarioDtoOut getById(Integer eId);
}
