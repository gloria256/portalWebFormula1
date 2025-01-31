package master.webapp.dto;

public class PilotoDtoOut {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String siglas;
    private Integer dorsal;
    private Integer estado;
    private String pais;
    private String dataurlb64;

    public String getDataurlb64() {
        return dataurlb64;
    }

    public void setDataurlb64(String dataurlb64) {
        this.dataurlb64 = dataurlb64;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getDorsal() {
        return dorsal;
    }

    public void setDorsal(Integer dorsal) {
        this.dorsal = dorsal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
}
