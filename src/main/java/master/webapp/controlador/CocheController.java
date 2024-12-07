package master.webapp.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import master.webapp.entidades.Coche;

@RestController
@RequestMapping("/portalWebFormula1")
public class CocheController {
	@Autowired
	private ModeloDeDatos BD;
	
	@CrossOrigin
	@GetMapping("/coches")
	public List<Coche> obtenerCoches() {
		return BD.obtenerTodosLosCoches();
	}
	
	@CrossOrigin
	@GetMapping("/coches/{id}")
	public Optional<Coche> obtenerUnCoche(@PathVariable("id") Integer id) {
		return BD.obtenerUnCoche(id);
	}
	
	@CrossOrigin
	@PostMapping("/coches")
	public Boolean agregarCoches(@RequestBody List<Coche> coche) {
		return BD.agregarCoche(coche);
	}
	
	@CrossOrigin
	@PutMapping("/coches")
	public List<Coche> actualizarCoches(@RequestBody List<Coche> coche) {
		return BD.actualizarCoche(coche);
	}
	
	@CrossOrigin
	@DeleteMapping("/coches")
	public Boolean eliminarCoches(@RequestBody List<Coche> coche) {
		return BD.eliminarCoches(coche);
	}
	
}
