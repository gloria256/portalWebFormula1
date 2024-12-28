package master.webapp.controlador;

import master.webapp.entidades.Equipo;
import master.webapp.services.IEquipoService;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final IEquipoService _service;

    @Autowired
    public EquipoController(IEquipoService _service) {
        this._service = _service;
    }

    @CrossOrigin
    @GetMapping({"", "/"})
    public ResponseEntity<List<Equipo>> getAll() {
        return ResponseEntity.ok(_service.getAll());
    }

    @CrossOrigin
    @GetMapping("/{eId}")
    public ResponseEntity<Equipo> getById(@PathVariable("eId") Integer eId) {
        return (_service.getById(eId) == null) ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(_service.getById(eId));
    }

    @CrossOrigin
    @PostMapping({"", "/"})
    public ResponseEntity<ResponseUtil> create(@RequestBody Equipo eEquipo) {
        ResponseUtil _response = _service.create(eEquipo);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @PutMapping({"", "/"})
    public ResponseEntity<ResponseUtil> update(@RequestBody Equipo eEquipo) {
        ResponseUtil _response = _service.update(eEquipo);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }
}