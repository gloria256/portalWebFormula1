package master.webapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class PilotoDtoIn {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String siglas;
    private Integer dorsal;
    private Byte[] foto;
    private String pais;
    private String twitter;
    private String dataurlb64;

    public String getDataurlb64() {
        return dataurlb64;
    }

    public void setDataurlb64(String dataurlb64) {
        this.dataurlb64 = dataurlb64;
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

    public Byte[] getFoto() {
        return foto;
    }

    public void setFoto(Byte[] foto) {
        this.foto = foto;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
