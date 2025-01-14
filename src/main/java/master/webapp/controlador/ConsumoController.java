package master.webapp.controlador;

import master.webapp.entidades.Circuito;
import master.webapp.entidades.Coche;
import master.webapp.entidades.ResultadoERS;
import master.webapp.repositorios.CircuitoRepositorio;
import master.webapp.repositorios.CocheRepositorio;
import master.webapp.services.ConsumoService;
import master.webapp.dao.CalculoERS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/consumo")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @Autowired
    private CocheRepositorio cocheRepositorio;

    @Autowired
    private CircuitoRepositorio circuitoRepositorio;

    @GetMapping("/calcular/{codigoCoche}/{nombreCircuito}/{margen}")
    public ResponseEntity<Map<String, Object>> calcularDesdeBBDD(@PathVariable String codigoCoche,
                                                                 @PathVariable String nombreCircuito,
                                                                 @PathVariable double margen) {
        Coche coche = cocheRepositorio.findByCodigo(codigoCoche);
        Optional<Circuito> circuito = circuitoRepositorio.findByNombre(nombreCircuito);

        if (coche == null || circuito.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Coche o Circuito no encontrados en la base de datos.");
            return ResponseEntity.badRequest().body(error);
        }

        double consumoPorVuelta = consumoService.calcularConsumoPorVuelta(coche, circuito.get());
        double consumoTotal = consumoService.calcularConsumoTotal(coche, circuito.get());
        double combustibleOptimo = consumoService.calcularCombustibleOptimo(coche, circuito.get(), margen);

        Map<String, Object> response = new HashMap<>();
        response.put("coche", coche.getNombre());
        response.put("circuito", circuito.get().getNombre());
        response.put("consumoPorVuelta", consumoPorVuelta);
        response.put("consumoTotal", consumoTotal);
        response.put("combustibleOptimo", combustibleOptimo);

        return ResponseEntity.ok(response);
    }
}