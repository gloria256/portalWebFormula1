package master.webapp.dao;

import master.webapp.entidades.Equipo;
import master.webapp.repositorios.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoDaoImpl implements IEquipoDao {

    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoDaoImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public Equipo getById(Integer eId) {
        return equipoRepository.findById(eId).orElse(null);
    }
}
