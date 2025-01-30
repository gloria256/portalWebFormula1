package master.webapp.controlador;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import master.webapp.entidades.Votacion;
import master.webapp.entidades.VotacionEmitida;

@RestController
@RequestMapping("/portalWebFormula1")
public class VotacionesController {
	@Autowired
	private ModeloDeDatos BD;
		
	@CrossOrigin
	@GetMapping("/votacion")
	public List<Votacion> obtenerVotaciones() {
		return BD.obtenerTodasLasVotaciones();
	}
	
	@CrossOrigin
	@GetMapping("/votacion/{id}")
	public Optional<Votacion> obtenerUnaVotacion(@PathVariable("id") Integer id) {
		return BD.obtenerUnaVotacion(id);
	}
	
	@CrossOrigin
	@PostMapping("/votacion")
	public ResponseEntity<String>  registrarVotacion(@RequestBody List<Votacion> votacion) {
		if(BD.registrarVotacion(votacion)) {
			return new ResponseEntity<>("votacion agregada con exito", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@PutMapping("/votacion")
	public List<Votacion> actualizarVotaciones(@RequestBody List<Votacion> votacion) {
		return BD.actualizarVotaciones(votacion);
	}
	
	@CrossOrigin
	@DeleteMapping("/votacion")
	public ResponseEntity<String> eliminarVotacion(@RequestBody List<Votacion> votacion) {
		if(BD.eliminarVotacion(votacion)) {
			return new ResponseEntity<>("Eliminacion exitosa", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	}
	
	
	//-------------------------
	// VOTACIONES EMITIDAS
	// ------------------------
	
	@CrossOrigin
	@GetMapping("/votaciones/{id}")
	public Map<String, Object> obtenerDatosVotacion(@PathVariable("id") Integer id) {
		return BD.obtenerDatosVotacion(id).get(0);	
	}
	
	@CrossOrigin
	@GetMapping("/votaciones")
	public List<Map<String, Object>> obtenerTodasLasVotacionesEmitidas() {
		return BD.obtenerDatosVotacion(null);	
	}
			
	@CrossOrigin
	@PostMapping("/votaciones")
	public ResponseEntity<String> registrarVotacionEmitida(@RequestBody VotacionEmitida votacionEmitida) {		
		if(BD.registrarVotacionEmitida(votacionEmitida)) {
			return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	}
			
	@CrossOrigin
	@PutMapping("/votaciones")
	public List<VotacionEmitida> actualizarVotacionEmitida(@RequestBody List<VotacionEmitida> votacionEmitida) {
		return BD.actualizarVotacionEmitida(votacionEmitida);
	}
			
	@CrossOrigin
	@DeleteMapping("/votaciones")
	public ResponseEntity<String> eliminarVotacionEmitida(@RequestBody List<VotacionEmitida> votacionEmitida) {
		if(BD.eliminarVotacionEmitida(votacionEmitida)) {
			return new ResponseEntity<>("Eliminacion exitosa", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	}
	

}
