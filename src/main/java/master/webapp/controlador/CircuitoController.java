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
	public Boolean agregarCircuitos(@RequestBody List<Circuito> circuito) {
		return BD.agregarCircuitos(circuito);
	}
	
	@CrossOrigin
	@PutMapping("/circuitos")
	public List<Circuito> actualizarCircuitos(@RequestBody List<Circuito> circuito) {
		return BD.actualizarCircuitos(circuito);
	}
	
	@CrossOrigin
	@DeleteMapping("/circuitos")
	public Boolean eliminarCircuitos(@RequestBody List<Circuito> circuito) {
		return BD.eliminarCircuitos(circuito);
	}

}
