package master.webapp.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

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
import master.webapp.entidades.Piloto;
import master.webapp.entidades.Votacion;
import master.webapp.entidades.VotacionEmitida;

@RestController
@RequestMapping("/portalWebFormula1")
public class Controlador {
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
	
	
	//-------------------------
	// OBTENER DATOS VOTACIONES
	// ------------------------
	
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
	public Boolean registrarVotacion(@RequestBody List<Votacion> votacion) {
		return BD.registrarVotacion(votacion);
	}
	
	@CrossOrigin
	@PutMapping("/votacion")
	public List<Votacion> actualizarVotaciones(@RequestBody List<Votacion> votacion) {
		return BD.actualizarVotaciones(votacion);
	}
	
	@CrossOrigin
	@DeleteMapping("/votacion")
	public Boolean eliminarVotacion(@RequestBody List<Votacion> votacion) {
		return BD.eliminarVotacion(votacion);
	}
	
	
	//-------------------------
	// PILOTOS
	// ------------------------
		
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
	
	
	
	//-------------------------
	// VOTACIONES EMITIDAS
	// ------------------------
	
	@CrossOrigin
	@GetMapping("/ResultadoVotaciones/{id}")
	public Map<String, Object> obtenerDatosVotacion(@PathVariable("id") Integer id) {
	
		Map<String, Object> datosRetornar = new HashMap<>();
		Optional<Votacion> listDatosVotacion = BD.obtenerUnaVotacion(id);
		
		//si existe la votación, busco las votaciones emitidas por id de la votación
		if(listDatosVotacion.isPresent()) {
			Votacion datosVotacion = listDatosVotacion.get();
			Integer votacionId = datosVotacion.getId();
			datosRetornar.put("id",votacionId);
			datosRetornar.put("title",datosVotacion.getTitulo());
			datosRetornar.put("endDate", datosVotacion.getLimite());
			
			List<Integer> ListaPilotosIdUnicos = new ArrayList<>();
			ArrayList<Map<String, Object>> options = new ArrayList<>();
			List<VotacionEmitida> listaVotacionesEmitidasPorIdVotacion = BD.buscarVotacionesEmitidasPorIdVotacion(votacionId);

			//Por cada votacion emitida perteneciente a la votación con ID:id
			//se extraen los datos de los pilotos y se agregar al retorno.
			for (VotacionEmitida elemento : listaVotacionesEmitidasPorIdVotacion) {
				Integer pilotoId = elemento.getPilotoId();
				
				Map<String, Object> opciones = new HashMap<>();
				Optional<Piloto> listaDatosPiloto = BD.obtenerUnPiloto(pilotoId);
				
				if (listaDatosPiloto.isPresent()) {					
					if(!ListaPilotosIdUnicos.contains(pilotoId)) {
						Piloto datosPiloto = listaDatosPiloto.get();
						Integer conteoPilotos = BD.conteoVotosPorIdVotacionAndIdPiloto(votacionId,pilotoId);
						
						opciones.put("id", datosPiloto.getId());
						opciones.put("name", datosPiloto.getNombre());
						opciones.put("team", "");
						opciones.put("votes", conteoPilotos);
						
						options.add(opciones);
						ListaPilotosIdUnicos.add(datosPiloto.getId());
					}
					
				}

			}
			
			datosRetornar.put("options",options);

		}
		return datosRetornar;
	}
	
	@CrossOrigin
	@GetMapping("/votaciones")
	public List<VotacionEmitida> obtenerTodasLasVotacionesEmitidas() {
		return BD.obtenerTodasLasVotacionesEmitidas();
	}
			
	@CrossOrigin
	@GetMapping("/votaciones/{id}")
	public Optional<VotacionEmitida> obtenerUnaVotacionEmitida(@PathVariable("id") Integer id) {
		return BD.obtenerUnaVotacionEmitida(id);
	}
	
	public Boolean validacionEmail(String email) {
	    String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    if(Pattern.matches(EMAIL_REGEX, email)) {
	    	return true;
	    }
	    return false;
	}
			
	@CrossOrigin
	@PostMapping("/votaciones")
	public Boolean registrarVotacionEmitida(@RequestBody VotacionEmitida votacionEmitida) {		
		String email = votacionEmitida.getEmail();
		Boolean existeEmail = BD.existeEmailVotacionEmitida(email);
		if(existeEmail || !validacionEmail(email)) {
			return false;
		}
		return BD.registrarVotacionEmitida(votacionEmitida);
	}
			
	@CrossOrigin
	@PutMapping("/votaciones")
	public List<VotacionEmitida> actualizarVotacionEmitida(@RequestBody List<VotacionEmitida> votacionEmitida) {
		return BD.actualizarVotacionEmitida(votacionEmitida);
	}
			
	@CrossOrigin
	@DeleteMapping("/votaciones")
	public Boolean eliminarVotacionEmitida(@RequestBody List<VotacionEmitida> votacionEmitida) {
		return BD.eliminarVotacionEmitida(votacionEmitida);
	}
	

}
