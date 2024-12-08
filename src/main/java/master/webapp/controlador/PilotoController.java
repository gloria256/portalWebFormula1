package master.webapp.controlador;

import java.util.List;
import java.util.Optional;

import master.webapp.dto.PilotoDtoIn;
import master.webapp.dto.PilotoDtoOut;
import master.webapp.services.IPilotoService;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import master.webapp.ModeloDeDatos;
import master.webapp.entidades.Piloto;

@RestController
@RequestMapping("/portalWebFormula1")
public class PilotoController {
	@Autowired
	private ModeloDeDatos BD;
	@Autowired
	private IPilotoService _service;
		
	@CrossOrigin
	@GetMapping("/piloto")
	public List<Piloto> obtenerTodosLosPilotos() {
		return BD.obtenerTodosLosPilotos();
	}
		
	@CrossOrigin
	@GetMapping("/piloto/{id}")
	public Optional<Piloto> obtenerUnPiloto(@PathVariable("id") Integer id) {
		return BD.obtenerUnPiloto(id);
	}
		
	@CrossOrigin
	@PostMapping("/piloto")
	public Boolean registrarPilotos(@RequestBody List<Piloto> piloto) {
		return BD.registrarPilotos(piloto);
	}
		
	@CrossOrigin
	@PutMapping("/piloto")
	public List<Piloto> actualizarPilotos(@RequestBody List<Piloto> piloto) {
		return BD.actualizarPilotos(piloto);
	}
		
	@CrossOrigin
	@DeleteMapping("/piloto")
	public Boolean eliminarPilotos(@RequestBody List<Piloto> piloto) {
		return BD.eliminarPilotos(piloto);
	}


	@CrossOrigin
	@GetMapping("/pilotos/{eId}")
	public ResponseEntity<Piloto> getById(@PathVariable("eId") Integer eId) {
		return (_service.getById(eId) == null) ?
				ResponseEntity.notFound().build() : ResponseEntity.ok(_service.getById(eId));
	}

	@CrossOrigin
	@GetMapping("/pilotos/equipo/{eEquipoId}")
	public List<PilotoDtoOut> getAllByEquipoId(@PathVariable("eEquipoId") Integer eEquipoId) {
		return _service.getAllByEquipoId(eEquipoId);
	}

	@CrossOrigin
	@PostMapping(value = {"/pilotos"})
	public ResponseEntity<ResponseUtil> create(@RequestBody PilotoDtoIn ePiloto) {
		ResponseUtil _response = _service.create(ePiloto);
		if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
		return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
	}

	@CrossOrigin
	@PutMapping(value = {"/pilotos"})
	public ResponseEntity<ResponseUtil> update(@RequestBody PilotoDtoIn ePiloto) {
		ResponseUtil _response = _service.update(ePiloto);
		if (_response.getStatus().compareToIgnoreCase(ConstantsUtil.SUCCESS) == 0) return ResponseEntity.ok(_response);
		return new ResponseEntity<>(_response, HttpStatus.FAILED_DEPENDENCY);
	}

	@CrossOrigin
	@GetMapping(value={"/pilotos/{eId}/siglas/{eSigla}/exists", "/pilotos/siglas/{eSigla}/exists"})
	public ResponseEntity<Boolean> getById(@PathVariable("eId") Optional<Integer> eId, @PathVariable("eSigla") String eSigla) {
		return ( _service.existSiglas(eSigla, 1, eId) ) ?
				ResponseEntity.ok(true): ResponseEntity.ok(false);
	}
}
