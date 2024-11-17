package master.webapp.controlador;

import master.webapp.dto.UsuarioDtoIn;
import master.webapp.dto.UsuarioDtoOut;
import master.webapp.services.IUsuarioRegistradoService;
import master.webapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRegistradoController {
    @Autowired
    private IUsuarioRegistradoService _service;

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<UsuarioDtoOut>> getAll() {
        return ResponseEntity.ok(_service.getAll());
    }

    @CrossOrigin
    @GetMapping("/{eId}")
    public ResponseEntity<UsuarioDtoOut> getById(@PathVariable("eId") Integer eId) {
        return (_service.getById(eId) == null) ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(_service.getById(eId));
    }

    @CrossOrigin
    @PostMapping(value = {"", "/"})
    public ResponseUtil create(@RequestBody UsuarioDtoIn eUsuario) {
        return _service.create(eUsuario);
    }

    @CrossOrigin
    @PutMapping(value = {"", "/"})
    public ResponseUtil update(@RequestBody UsuarioDtoIn eUsuario) {
        return _service.update(eUsuario);
    }
}


