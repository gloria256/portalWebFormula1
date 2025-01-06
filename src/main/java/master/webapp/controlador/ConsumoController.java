package master.webapp.controlador;


import master.webapp.entidades.Circuito;
import master.webapp.entidades.Coche;
import master.webapp.repositorios.CircuitoRepositorio;
import master.webapp.repositorios.CocheRepositorio;
import master.webapp.services.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/consumo")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @Autowired
    private CocheRepositorio cocheRepositorio;

    @Autowired
    private CircuitoRepositorio circuitoRepositorio;

    // Nuevo endpoint para calcular consumo desde la BBDD
    @GetMapping("/calcular/{codigoCoche}/{nombreCircuito}/{margen}")
    public String calcularDesdeBBDD(@PathVariable String codigoCoche,
                                    @PathVariable String nombreCircuito,
                                    @PathVariable double margen) {
        // Buscar coche y circuito en la base de datos
        Coche coche = cocheRepositorio.findByCodigo(codigoCoche);
        Circuito circuito = circuitoRepositorio.findByNombre(nombreCircuito);

        if (coche == null || circuito == null) {
            return "Coche o Circuito no encontrados en la base de datos.";
        }

        // Calcular consumos
        double consumoPorVuelta = consumoService.calcularConsumoPorVuelta(coche, circuito);
        double consumoTotal = consumoService.calcularConsumoTotal(coche, circuito);
        double combustibleOptimo = consumoService.calcularCombustibleOptimo(coche, circuito, margen);

        // Formatear respuesta
        return String.format(
                "Resultados de Prueba:%n" +
                        "Coche: %s%n" +
                        "Circuito: %s%n" +
                        "Consumo por vuelta: %.2f L%n" +
                        "Consumo total: %.2f L%n" +
                        "Combustible óptimo (%.2f%% margen): %.2f L",
                coche.getNombre(),
                circuito.getNombre(),
                consumoPorVuelta,
                consumoTotal,
                margen,
                combustibleOptimo
        );
    }
}
