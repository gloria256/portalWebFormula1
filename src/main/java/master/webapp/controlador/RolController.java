package master.webapp.controlador;

import master.webapp.entidades.Rol;
import master.webapp.repositorios.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolRepository _repository;

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Rol>> getAll() {
        return ResponseEntity.ok(_repository.findAll());
    }

    @CrossOrigin
    @GetMapping("/{eId}")
    public ResponseEntity<Rol> getById(@PathVariable("eId") Integer eId) {
        return _repository.findById(eId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}