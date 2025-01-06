package master.webapp.services;


import master.webapp.entidades.Circuito;
import master.webapp.entidades.Coche;
import org.springframework.stereotype.Service;

@Service
public class ConsumoService {

    /**
     * Calcula el consumo de combustible por vuelta.
     *
     * @param coche    El coche del cual se quiere calcular el consumo.
     * @param circuito El circuito donde se corre.
     * @return Consumo por vuelta en litros.
     */
    public double calcularConsumoPorVuelta(Coche coche, Circuito circuito) {
        double consumoPorKm = coche.getConsumo() / 100;
        return consumoPorKm * circuito.getLongitud();

    }

    /**
     * Calcula el consumo total para todas las vueltas de la carrera.
     *
     * @param coche    El coche del cual se quiere calcular el consumo.
     * @param circuito El circuito donde se corre.
     * @return Consumo total en litros.
     */
    public double calcularConsumoTotal(Coche coche, Circuito circuito) {
        double consumoPorVuelta = calcularConsumoPorVuelta(coche, circuito);
        return consumoPorVuelta * circuito.getNumeroVueltas();
    }

    /**
     * Calcula el combustible óptimo considerando un margen de seguridad.
     *
     * @param coche    El coche del cual se quiere calcular el consumo.
     * @param circuito El circuito donde se corre.
     * @param margen   Margen adicional en porcentaje (por ejemplo, 10%).
     * @return Cantidad óptima de combustible en litros.
     */
    public double calcularCombustibleOptimo(Coche coche, Circuito circuito, double margen) {
        double consumoTotal = calcularConsumoTotal(coche, circuito);
        return consumoTotal * (1 + margen / 100);
    }


}
