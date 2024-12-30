package master.webapp.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="piloto")
public class Piloto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false,length=255)//validacion a nivel de base de datos
	private String nombre;
	
	@Column(nullable=false,length=255)
	private String apellidos;
	
	@Column(nullable = false,length=255)
	private String siglas;
	
	@Column(nullable=false,length=10)
	private Integer dorsal;
	
	@Lob //tipo de datos grande
	@Column(nullable=true)
	private Byte[] foto;
	
	@Column(nullable=false,length=255)
	private String pais;
	
	@Column(nullable=true,length=255)
	private String twitter;
	
	@ManyToMany
	@JoinTable(name="votaciones_emitidas",joinColumns = {
		@JoinColumn(name="piloto_id",referencedColumnName="id")
	}, inverseJoinColumns = {
			@JoinColumn(name="votacion_id", referencedColumnName="id")
	})
	@JsonIgnoreProperties("pilotos")
	private List<Votacion> votacion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_equipo")
	private Equipo equipo;

    @Column(name = "dataurlb64")
    private String dataurlb64;

	@ColumnDefault("1")
	@Column(name = "estado", nullable = false)
	private Integer estado;

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getDataurlb64() {
        return dataurlb64;
    }

    public void setDataurlb64(String dataurlb64) {
        this.dataurlb64 = dataurlb64;
    }

    public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}


	//----------------------
	//Se generan los Getters
	//----------------------

	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getSiglas() {
		return siglas;
	}
	public Integer getDorsal() {
		return dorsal;
	}
	public Byte[] getFoto() {
		return foto;
	}
	public String getPais() {
		return pais;
	}
	public String getTwitter() {
		return twitter;
	}
	public List<Votacion> getVotacion() {
		return votacion;
	}
	
	
	//----------------------
	//Se generan los Setters
	//----------------------
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}
	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public void setVotacion(List<Votacion> votacion) {
		this.votacion = votacion;
	}
	
	
	//OTROS METODOS
	
	public void addVotacion(Votacion votacion) {
		if(votacion != null) {
			getVotacion().add(votacion);
		}
	}
	public void removeVotacion(Votacion votacion) {
		if(votacion != null) {
			getVotacion().remove(votacion);
		}
	}
}
