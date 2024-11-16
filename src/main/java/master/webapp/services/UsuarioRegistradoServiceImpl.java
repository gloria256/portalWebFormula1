package master.webapp.services;

import master.webapp.dao.IUsuarioRegistradoDAO;
import master.webapp.dto.UsuarioDtoOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioRegistradoServiceImpl implements IUsuarioRegistradoService{
    @Autowired
    private IUsuarioRegistradoDAO _dao;

    @Autowired
    private ModelMapper _modelMapper;

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
        return (_dao.getById(eId) == null) ? null : _modelMapper.map(_dao.getById(eId), UsuarioDtoOut.class);
    }
}
