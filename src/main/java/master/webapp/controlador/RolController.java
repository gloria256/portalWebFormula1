package master.webapp.controlador;

import master.webapp.entidades.Rol;
import master.webapp.services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final IRolService _service;

    @Autowired
    public RolController(IRolService service) {
        this._service = service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Rol>> getAll() {
        return ResponseEntity.ok(_service.getAll());
    }

    @CrossOrigin
    @GetMapping("/{eId}")
    public ResponseEntity<Rol> getById(@PathVariable("eId") Integer eId) {
        return (_service.getById(eId) == null) ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(_service.getById(eId));
    }
}