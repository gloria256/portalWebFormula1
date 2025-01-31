package master.webapp.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @Column(name = "twitter")
    private String twitter;

    @Lob
    @Column(name = "logo", columnDefinition="LONGTEXT")
    private String logo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "responsables_equipo",
            joinColumns = @JoinColumn(name = "equipo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<UsuarioRegistrado> responsables = new LinkedHashSet<>();

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Set<UsuarioRegistrado> getResponsables() {
        return responsables;
    }

    public void setResponsables(Set<UsuarioRegistrado> responsables) {
        this.responsables = responsables;
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

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}