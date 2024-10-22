package master.webapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import aj.org.objectweb.asm.Type;
import master.webapp.entidades.Circuito;
import master.webapp.repositorios.CircuitoRepositorio;

@Component
public class ModeloDeDatos {
	
	@Autowired
	private CircuitoRepositorio circuitoRepositorio;
    
    
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
    
    public Boolean agregarCircuitos(@RequestBody List<Circuito> circuito) {
    	try {
    		List<Circuito> resultadoPost = this.circuitoRepositorio.saveAll(circuito);
    		Boolean resultado =  resultadoPost.isEmpty() ? false : true;
    		return resultado;     
		} catch (Exception e) {
	    	System.out.println("No es posible guardar los datos del o los circuitos");
	    	System.out.println(e);
	    	return false;
		}    			
    }
    
    public List<Circuito> actualizarCircuitos(@RequestBody List<Circuito> circuito) {
    	List<Circuito> registrosActualizados = new ArrayList<>();
    	Integer id;
    	Boolean existeRegistro;
    	try {
    		/*Si existe lo actualiza, devuelve los actualizados*/
    		for (Circuito elemento : circuito) {
    			id = elemento.getId();
				existeRegistro = this.circuitoRepositorio.existsById(id);
				if (existeRegistro) {
					Circuito resultadoPUT = this.circuitoRepositorio.save(elemento);
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
    
    public Boolean eliminarCircuitos(@RequestBody List<Circuito> circuito) {
    	try {
    		this.circuitoRepositorio.deleteAll(circuito);
    		return true;     
		} catch (Exception e) {
	    	System.out.println("No es posible eliminar");
	    	System.out.println(e);
	    	return false;
		}    			
    }
}
