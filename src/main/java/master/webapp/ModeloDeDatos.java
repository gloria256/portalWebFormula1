package master.webapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import master.webapp.entidades.Circuito;
import master.webapp.entidades.Coche;
import master.webapp.entidades.Piloto;
import master.webapp.entidades.Votacion;
import master.webapp.entidades.VotacionEmitida;
import master.webapp.repositorios.CircuitoRepositorio;
import master.webapp.repositorios.CocheRepositorio;
import master.webapp.repositorios.PilotoRepositorio;
import master.webapp.repositorios.VotacionEmitidaRepositorio;
import master.webapp.repositorios.VotacionRepositorio;

@Component
public class ModeloDeDatos {

	@Autowired
	private CircuitoRepositorio circuitoRepositorio;

	@Autowired
	private VotacionRepositorio votacionRepositorio;

	@Autowired
	private PilotoRepositorio pilotoRepositorio;

	@Autowired
	private VotacionEmitidaRepositorio votacionEmitidaRepositorio;

	@Autowired
	private CocheRepositorio cocheRepositorio;

	public List<Circuito> obtenerTodosLosCircuitos() {
		try {
			return this.circuitoRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos de los circuitos");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Optional<Circuito> obtenerUnCircuito(Integer id) {
		try {
			Optional<Circuito> resultado = this.circuitoRepositorio.findById(id);
			resultado = resultado.isPresent() ? resultado : Optional.empty();
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos del circuito con Id: " + id);
			System.out.println(e);
			return Optional.empty();
		}
	}

	/*public Boolean agregarCircuito(@RequestBody List<Circuito> circuito) {
		try {
			List<Circuito> resultadoPost = this.circuitoRepositorio.saveAll(circuito);
			Boolean resultado = resultadoPost.isEmpty() ? false : true;
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible guardar los datos del o los circuitos");
			System.out.println(e);
			return false;
		}
	}*/
	
	public ResponseEntity<Map<String, Object>> agregarCircuito(@RequestBody Circuito circuito) {
		Map<String, Object> error = new HashMap<>();
		try {
			Map<String, Object> response = new HashMap<>();
			Circuito resultadoPost = this.circuitoRepositorio.save(circuito);
			System.out.println("AGREGAR CIRCUITO CON EXITO");
			response.put("mensage","El circuito ha sido agregado con exito");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println("No es posible guardar los datos del o los circuitos");
			System.out.println(e);
			
			error.put("error", "No es posible agregar el circuito a la base de datos");
            return ResponseEntity.badRequest().body(error);
		}
	}

	public ResponseEntity<Map<String, Object>> actualizarCircuito(@RequestBody Circuito circuito) {
		Integer id;
		Boolean existeRegistro;
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> error = new HashMap<>();
		try {
			System.out.println(circuito.getCiudad());
			System.out.println(circuito.getId());
			/* Si existe lo actualiza */
			id = circuito.getId();
			existeRegistro = this.circuitoRepositorio.existsById(id);
			if (existeRegistro) {
				Circuito newCircuit = new Circuito();
				newCircuit.setId(id);
				newCircuit.setCiudad(circuito.getCiudad());
				newCircuit.setCurvasLentas(circuito.getCurvasLentas());
				newCircuit.setCurvasMedia(circuito.getCurvasMedia());
				newCircuit.setCurvasRapidas(circuito.getCurvasRapidas());
				newCircuit.setFecha(circuito.getFecha());
				newCircuit.setLongitud(circuito.getLongitud());
				newCircuit.setNombre(circuito.getNombre());
				newCircuit.setNumeroVueltas(circuito.getNumeroVueltas());
				newCircuit.setPais(circuito.getPais());
				newCircuit.setTrazado(circuito.getTrazado());
				
				Circuito resultadoPUT = this.circuitoRepositorio.save(newCircuit);
				response.put("mensage","El circuito ha sido actualizado con exito");
				return ResponseEntity.ok(response);
			}else {
				error.put("error", "El circuito No se encuentra en la base de datos");
	            return ResponseEntity.badRequest().body(error);
			}

		} catch (Exception e) {
			System.out.println("No es posible actualizar");
			System.out.println(e);
			error.put("error", "No es posible actualizar");
            return ResponseEntity.badRequest().body(error);
		}
	}

	public ResponseEntity<Map<String, Object>> eliminarCircuito(@RequestBody Integer idCircuito) {
		Map<String, Object> error = new HashMap<>();
		try {
			Boolean existeRegistro = this.circuitoRepositorio.existsById(idCircuito);
			Map<String, Object> response = new HashMap<>();
			Optional<Circuito> circuito = this.circuitoRepositorio.findById(idCircuito);
			
			System.out.println("ENTRE A ELIMINAR CIRCUITO");
			System.out.println(idCircuito);
			
			/*Si el circuito está en calendario no eliminar*/
			if (circuito.isPresent()) {
				System.out.println(circuito.get().getFecha());
				if(circuito.get().getFecha() == null) {
					this.circuitoRepositorio.deleteById(idCircuito);
					response.put("mensage","El circuito ha sido eliminado con exito");
					return ResponseEntity.ok(response);
				}else {
		            error.put("error", "El circuito se encuentra en el calendario. No es posible eliminarlo");
		            return ResponseEntity.badRequest().body(error);
				}
				
			}else {
				error.put("error", "El circuito, no existe en la base de datos");
	            return ResponseEntity.badRequest().body(error);
			}
			
		} catch (Exception e) {
			System.out.println("No es posible eliminar");
			System.out.println(e);
			error.put("error", "Falla interna. No es posible eliminar");
            return ResponseEntity.badRequest().body(error);
		}
	}

	// ------------
	// CRUD PILOTOS
	// ------------

	public List<Piloto> obtenerTodosLosPilotos() {
		try {
			return this.pilotoRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos de todos los piloto");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Optional<Piloto> obtenerUnPiloto(Integer id) {
		try {
			Optional<Piloto> resultado = this.pilotoRepositorio.findById(id);
			resultado = resultado.isPresent() ? resultado : Optional.empty();
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos del piloto con Id: " + id);
			System.out.println(e);
			return Optional.empty();
		}
	}

	public Boolean registrarPilotos(@RequestBody List<Piloto> piloto) {
		try {
			List<Piloto> resultadoPost = this.pilotoRepositorio.saveAll(piloto);
			Boolean resultado = resultadoPost.isEmpty() ? false : true;
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible guardar los datos del piloto");
			System.out.println(e);
			return false;
		}
	}

	public List<Piloto> actualizarPilotos(@RequestBody List<Piloto> piloto) {
		List<Piloto> registrosActualizados = new ArrayList<>();
		Integer id;
		Boolean existeRegistro;
		try {
			/* Si existe lo actualiza, devuelve los actualizados */
			for (Piloto elemento : piloto) {
				id = elemento.getId();
				existeRegistro = this.pilotoRepositorio.existsById(id);
				if (existeRegistro) {
					Piloto resultadoPUT = this.pilotoRepositorio.save(elemento);
					registrosActualizados.add(elemento);
				}

			}

			return registrosActualizados;
		} catch (Exception e) {
			System.out.println("No es posible actualizar");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Boolean eliminarPilotos(@RequestBody List<Piloto> piloto) {
		try {
			this.pilotoRepositorio.deleteAll(piloto);
			return true;
		} catch (Exception e) {
			System.out.println("No es posible eliminar");
			System.out.println(e);
			return false;
		}
	}

	// ---------------
	// CRUD VOTACIONES
	// ---------------

	public List<Votacion> obtenerTodasLasVotaciones() {
		try {
			return this.votacionRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos de todas las votaciones");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Optional<Votacion> obtenerUnaVotacion(Integer id) {
		try {
			Optional<Votacion> resultado = this.votacionRepositorio.findById(id);
			resultado = resultado.isPresent() ? resultado : Optional.empty();
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos de la votación con Id: " + id);
			System.out.println(e);
			return Optional.empty();
		}
	}

	public Boolean registrarVotacion(@RequestBody List<Votacion> votacion) {
		try {
			List<Votacion> resultadoPost = this.votacionRepositorio.saveAll(votacion);
			Boolean resultado = resultadoPost.isEmpty() ? false : true;
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible guardar los datos de las votaciones");
			System.out.println(e);
			return false;
		}
	}

	public List<Votacion> actualizarVotaciones(@RequestBody List<Votacion> votacion) {
		List<Votacion> registrosActualizados = new ArrayList<>();
		Integer id;
		Boolean existeRegistro;
		try {
			/* Si existe lo actualiza, devuelve los actualizados */
			for (Votacion elemento : votacion) {
				id = elemento.getId();
				existeRegistro = this.votacionRepositorio.existsById(id);
				if (existeRegistro) {
					Votacion resultadoPUT = this.votacionRepositorio.save(elemento);
					registrosActualizados.add(elemento);
				}

			}

			return registrosActualizados;
		} catch (Exception e) {
			System.out.println("No es posible actualizar");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Boolean eliminarVotacion(@RequestBody List<Votacion> votacion) {
		try {
			this.votacionRepositorio.deleteAll(votacion);
			return true;
		} catch (Exception e) {
			System.out.println("No es posible eliminar");
			System.out.println(e);
			return false;
		}
	}

	// --------------------------
	// CRUD VOTACIONES EMITIDAS
	// ------------------------

	public List<VotacionEmitida> obtenerTodasLasVotacionesEmitidas() {
		try {
			return this.votacionEmitidaRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos de todas las votaciones emitidas");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Optional<VotacionEmitida> obtenerUnaVotacionEmitida(Integer id) {
		try {
			Optional<VotacionEmitida> resultado = this.votacionEmitidaRepositorio.findById(id);
			resultado = resultado.isPresent() ? resultado : Optional.empty();
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos de la votación emitida con Id: " + id);
			System.out.println(e);
			return Optional.empty();
		}
	}

	public List<VotacionEmitida> buscarVotacionesEmitidasPorIdVotacion(Integer votacionId) {
		try {
			List<VotacionEmitida> resultado = this.votacionEmitidaRepositorio.findByVotacionId(votacionId);
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos");
			System.out.println(e);
			return new ArrayList<VotacionEmitida>();
		}
	}

	public Integer conteoVotosPorIdVotacionAndIdPiloto(Integer votacionId, Integer pilotoId) {
		try {
			List<VotacionEmitida> resultado = this.votacionEmitidaRepositorio.findByVotacionIdAndPilotoId(votacionId,
					pilotoId);
			return resultado.size();
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos");
			System.out.println(e);
			return 0;
		}
	}

	public Boolean emailValido(String email) {
		String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if (Pattern.matches(EMAIL_REGEX, email)) {
			return true;
		}
		return false;
	}

	public Boolean registrarVotacionEmitida(@RequestBody VotacionEmitida votacionEmitida) {
		try {
			// Valida que el email sea válido y no haya votado antes en la votacion
			// especifica
			String email = votacionEmitida.getEmail();
			Boolean existeEmail = this.votacionEmitidaRepositorio.existsByVotacionIdAndEmail(votacionEmitida.getId(),
					email);
			if (existeEmail || !this.emailValido(email)) {
				return false;
			}
			VotacionEmitida resultadoPost = this.votacionEmitidaRepositorio.save(votacionEmitida);
			return true;
		} catch (Exception e) {
			System.out.println("No es posible guardar los datos de la votacionEmitida");
			System.out.println(e);
			return false;
		}
	}

	public List<VotacionEmitida> actualizarVotacionEmitida(@RequestBody List<VotacionEmitida> votacionEmitida) {
		List<VotacionEmitida> registrosActualizados = new ArrayList<>();
		Integer id;
		Boolean existeRegistro;
		try {
			/* Si existe lo actualiza, devuelve los actualizados */
			for (VotacionEmitida elemento : votacionEmitida) {
				id = elemento.getId();
				existeRegistro = this.votacionEmitidaRepositorio.existsById(id);
				if (existeRegistro) {
					VotacionEmitida resultadoPUT = this.votacionEmitidaRepositorio.save(elemento);
					registrosActualizados.add(elemento);
				}

			}

			return registrosActualizados;
		} catch (Exception e) {
			System.out.println("No es posible actualizar");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Boolean eliminarVotacionEmitida(@RequestBody List<VotacionEmitida> votacionEmitida) {
		try {
			this.votacionEmitidaRepositorio.deleteAll(votacionEmitida);
			return true;
		} catch (Exception e) {
			System.out.println("No es posible eliminar");
			System.out.println(e);
			return false;
		}
	}

	public ArrayList<Map<String, Object>> obtenerDatosVotacion(Integer id) {

		ArrayList<Map<String, Object>> datosRetornar = new ArrayList<>();
		List<Votacion> listDatosVotacion = new ArrayList<>();

		if (id != null) {
			Optional<Votacion> votacionOptional = this.obtenerUnaVotacion(id);
			votacionOptional.ifPresent(listDatosVotacion::add);
		} else {
			listDatosVotacion = this.obtenerTodasLasVotaciones();
		}

		for (Votacion votacion : listDatosVotacion) {
			Integer votacionId = votacion.getId();
			// si existe la votación, busco las votaciones emitidas
			// por id de la votación
			Map<String, Object> mapVotacion = new HashMap<>();
			mapVotacion.put("id", votacionId);
			mapVotacion.put("title", votacion.getTitulo());
			mapVotacion.put("endDate", votacion.getLimite());

			String textoPilotosVotacion = votacionRepositorio.findListaPilotosById(votacionId);
			// Eliminar '[' y ']'
			textoPilotosVotacion = textoPilotosVotacion.substring(1, textoPilotosVotacion.length() - 1);
			// Convertir los elementos a Integer y devolverlos en una lista
			String[] ListaPilotosVotacion = textoPilotosVotacion.split(",");

			ArrayList<Map<String, Object>> options = new ArrayList<>();
			// Por cada votacion emitida perteneciente a la votación con ID:id
			// se extraen los datos de los pilotos y se agregar al retorno.
			for (String idpiloto : ListaPilotosVotacion) {
				Integer pilotoId = Integer.parseInt(idpiloto.trim());
				Map<String, Object> opciones = new HashMap<>();
				Optional<Piloto> listaDatosPiloto = this.obtenerUnPiloto(pilotoId);

				if (listaDatosPiloto.isPresent()) {
					Piloto datosPiloto = listaDatosPiloto.get();
					Integer conteoPilotos = this.conteoVotosPorIdVotacionAndIdPiloto(votacionId, pilotoId);

					opciones.put("id", datosPiloto.getId());
					opciones.put("name", datosPiloto.getNombre());
					opciones.put("team", "");
					opciones.put("votes", conteoPilotos);

					options.add(opciones);
				}
			}

			mapVotacion.put("options", options);
			datosRetornar.add(mapVotacion);

		}

		return datosRetornar;
	}

	// --------------------------
	// CRUD COCHES
	//

	public List<Coche> obtenerTodosLosCoches() {
		try {
			return this.cocheRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos de los coches");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Optional<Coche> obtenerUnCoche(Integer id) {
		try {
			Optional<Coche> resultado = this.cocheRepositorio.findById(id);
			resultado = resultado.isPresent() ? resultado : Optional.empty();
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible obtener los datos del coche con Id: " + id);
			System.out.println(e);
			return Optional.empty();
		}
	}

	public Boolean agregarCoche(@RequestBody List<Coche> coche) {
		try {
			List<Coche> resultadoPost = this.cocheRepositorio.saveAll(coche);
			Boolean resultado = resultadoPost.isEmpty() ? false : true;
			return resultado;
		} catch (Exception e) {
			System.out.println("No es posible guardar los datos del o los coches");
			System.out.println(e);
			return false;
		}
	}

	public List<Coche> actualizarCoche(@RequestBody List<Coche> coche) {
		List<Coche> registrosActualizados = new ArrayList<>();
		Integer id;
		Boolean existeRegistro;
		try {
			/* Si existe lo actualiza, devuelve los actualizados */
			for (Coche elemento : coche) {
				id = elemento.getId();
				existeRegistro = this.cocheRepositorio.existsById(id);
				if (existeRegistro) {
					Coche resultadoPUT = this.cocheRepositorio.save(elemento);
					registrosActualizados.add(elemento);
				}

			}

			return registrosActualizados;
		} catch (Exception e) {
			System.out.println("No es posible actualizar");
			System.out.println(e);
			return new ArrayList<>();
		}
	}

	public Boolean eliminarCoches(@RequestBody List<Coche> coche) {
		try {
			this.cocheRepositorio.deleteAll(coche);
			return true;
		} catch (Exception e) {
			System.out.println("No es posible eliminar");
			System.out.println(e);
			return false;
		}
	}

}
