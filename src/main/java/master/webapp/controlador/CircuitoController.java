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
import master.webapp.entidades.Circuito;
import master.webapp.util.ConstantsUtil;
import master.webapp.util.ResponseUtil;

@RestController
@RequestMapping("/portalWebFormula1")
public class CircuitoController {
	@Autowired
	private ModeloDeDatos BD;
	
	@CrossOrigin
	@GetMapping("/circuitos")
	public List<Circuito> obtenerCircuitos() {
		return BD.obtenerTodosLosCircuitos();
	}
	
	@CrossOrigin
	@GetMapping("/circuitos/{id}")
	public Optional<Circuito> obtenerUnCircuito(@PathVariable("id") Integer id) {
		return BD.obtenerUnCircuito(id);
	}
	
	@CrossOrigin
	@PostMapping("/circuitos")
	public ResponseEntity<Map<String, Object>> agregarCircuitos(@RequestBody Circuito circuito) {
		System.out.println("AGREGAR CIRCUITO");
		return BD.agregarCircuito(circuito);
	}
	
	@CrossOrigin
	@PutMapping("/circuitos")
	public ResponseEntity<Map<String, Object>> actualizarCircuitos(@RequestBody Circuito circuito) {
		return BD.actualizarCircuito(circuito);
	}
	
	@CrossOrigin
	@DeleteMapping("/circuitos")
	public ResponseEntity<Map<String, Object>>  eliminarCircuitos(@RequestBody Integer idCircuito) {
		System.out.println("ENTRE A BACK");
		System.out.println(idCircuito);
		return BD.eliminarCircuito(idCircuito);
	}

}
