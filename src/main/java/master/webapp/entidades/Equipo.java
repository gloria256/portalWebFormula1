package master.webapp.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "twitter")
    private String twitter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}