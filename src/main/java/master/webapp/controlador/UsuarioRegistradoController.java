package master.webapp.controlador;

import master.webapp.dto.UsuarioDtoIn;
import master.webapp.dto.UsuarioDtoOut;
import master.webapp.services.IUsuarioRegistradoService;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRegistradoController {
    private final IUsuarioRegistradoService _service;

    @Autowired
    public UsuarioRegistradoController(IUsuarioRegistradoService _service) {
        this._service = _service;
    }

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
    public ResponseEntity<ResponseUtil> create(@RequestBody UsuarioDtoIn eUsuario) {
        ResponseUtil _response = _service.create(eUsuario);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @PutMapping(value = {"", "/"})
    public ResponseEntity<ResponseUtil> update(@RequestBody UsuarioDtoIn eUsuario) {
        ResponseUtil _response = _service.update(eUsuario);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @DeleteMapping(value = {"", "/"})
    public ResponseEntity<ResponseUtil> delete(@RequestBody UsuarioDtoIn eUsuario) {
        ResponseUtil _response = _service.delete(eUsuario);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @GetMapping({"/by/equipo", "/by/equipo/"})
    public ResponseEntity<List<UsuarioDtoOut>> getAllByEquipo() {
        return ResponseEntity.ok(_service.findByEquipoId());
    }

    @CrossOrigin
    @PostMapping(value = {"/by/equipo", "/by/equipo/"})
    public ResponseEntity<ResponseUtil> createByEquipo(@RequestBody UsuarioDtoIn eUsuario) {
        ResponseUtil _response = _service.addResponsableEquipo(eUsuario);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @PutMapping(value = {"/by/equipo", "/by/equipo/"})
    public ResponseEntity<ResponseUtil> updateResponsableEquipo(@RequestBody UsuarioDtoIn eUsuario) {
        ResponseUtil _response = _service.updateResponsableEquipo(eUsuario);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }

    @CrossOrigin
    @DeleteMapping("/{id}/by/equipo")
    public ResponseEntity<ResponseUtil> deleteResponsableEquipo(@PathVariable("id") Integer id) {
        ResponseUtil _response = _service.deleteResponsableEquipo(id);
        if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
        return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
    }
}


