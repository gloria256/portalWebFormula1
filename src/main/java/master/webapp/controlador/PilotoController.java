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
import master.webapp.entidades.Piloto;

@RestController
@RequestMapping("/portalWebFormula1")
public class PilotoController {
	@Autowired
	private ModeloDeDatos BD;
		
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

}
