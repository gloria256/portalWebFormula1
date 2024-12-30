package master.webapp.dao;

import master.webapp.entidades.Equipo;
import master.webapp.repositorios.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Equipo> getAll() {
        return equipoRepository.findAll();
    }

    @Override
    public void save(Equipo eEquipo) {
        equipoRepository.save(eEquipo);
    }

    @Override
    public Equipo getByResponsableId(Integer eResponsableId) {
        return equipoRepository.getByResponsableId(eResponsableId).orElse(null);
    }
}
