package master.webapp.controlador;

import master.webapp.dao.CalculoERS;
import master.webapp.entidades.Circuito;
import master.webapp.entidades.ResultadoERS;
import master.webapp.repositorios.CircuitoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ers")
public class CalculoERSController {

    @Autowired
    private CalculoERS calculoERS;

    @Autowired
    private CircuitoRepositorio circuitoRepositorio;

    @GetMapping("/calcular")
    public ResultadoERS calcularERS(@RequestParam String circuitoNombre,
                                    @RequestParam String modoConduccion) {
        Circuito circuito = circuitoRepositorio.findByNombre(circuitoNombre)
                .orElseThrow(() -> new RuntimeException("Circuito no encontrado: " + circuitoNombre));
        return calculoERS.calcularGananciaERS(circuito, modoConduccion);
    }
}
