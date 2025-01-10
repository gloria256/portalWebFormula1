package master.webapp.dao;


import master.webapp.entidades.Circuito;
import master.webapp.entidades.ResultadoERS;
import org.springframework.stereotype.Component;

@Component
public class CalculoERS {

    private static final double CAPACIDAD_MAXIMA_BATERIA_KWH = 1.20; // 4MJ
    private static final double LIMITE_ENERGIA_POR_VUELTA_KWH = 0.60; // 2MJ

    // Valores base de energía recuperada por curva
    private static final double ERS_CURVA_LENTA = 0.02; // kWh
    private static final double ERS_CURVA_MEDIA = 0.04; // kWh
    private static final double ERS_CURVA_RAPIDA = 0.06; // kWh

    public ResultadoERS calcularGananciaERS(Circuito circuito, String modoConduccion) {
        // Modificar valores según el modo de conducción
        double modificador = obtenerModificadorModo(modoConduccion);
        double ersCurvaLenta = ERS_CURVA_LENTA + (ERS_CURVA_LENTA * modificador);
        double ersCurvaMedia = ERS_CURVA_MEDIA + (ERS_CURVA_MEDIA * modificador);
        double ersCurvaRapida = ERS_CURVA_RAPIDA + (ERS_CURVA_RAPIDA * modificador);

        // Calcular energía recuperada por vuelta
        double energiaPorVuelta = (ersCurvaLenta * circuito.getCurvasLentas() +
                ersCurvaMedia * circuito.getCurvasMedia() +
                ersCurvaRapida * circuito.getCurvasRapidas());

        // Limitar la energía recuperada por vuelta
        energiaPorVuelta = Math.min(energiaPorVuelta, LIMITE_ENERGIA_POR_VUELTA_KWH);

        // Calcular vueltas necesarias para cargar completamente la batería
        int vueltasParaCargaCompleta = (int) Math.ceil(CAPACIDAD_MAXIMA_BATERIA_KWH / energiaPorVuelta);

        return new ResultadoERS(energiaPorVuelta, vueltasParaCargaCompleta);
    }

    private double obtenerModificadorModo(String modoConduccion) {
        switch (modoConduccion.toLowerCase()) {
            case "ahorrador":
                return 0.05; // +5%
            case "normal":
                return -0.25; // -25%
            case "deportivo":
                return -0.60; // -60%
            default:
                throw new IllegalArgumentException("Modo de conducción no válido: " + modoConduccion);
        }
    }
}
