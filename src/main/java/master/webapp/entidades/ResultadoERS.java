package master.webapp.entidades;

public class ResultadoERS {
    private double energiaPorVuelta;
    private int vueltasParaCargaCompleta;

    public ResultadoERS(double energiaPorVuelta, int vueltasParaCargaCompleta) {
        this.energiaPorVuelta = energiaPorVuelta;
        this.vueltasParaCargaCompleta = vueltasParaCargaCompleta;
    }

    public double getEnergiaPorVuelta() {
        return energiaPorVuelta;
    }

    public void setEnergiaPorVuelta(double energiaPorVuelta) {
        this.energiaPorVuelta = energiaPorVuelta;
    }

    public int getVueltasParaCargaCompleta() {
        return vueltasParaCargaCompleta;
    }

    public void setVueltasParaCargaCompleta(int vueltasParaCargaCompleta) {
        this.vueltasParaCargaCompleta = vueltasParaCargaCompleta;
    }
}
