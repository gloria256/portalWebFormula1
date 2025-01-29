package master.webapp.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "piloto")
public class Piloto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 255) // validacion a nivel de base de datos
	private String nombre;

	@Column(nullable = false, length = 255)
	private String apellidos;

	@Column(nullable = false, length = 255)
	private String siglas;

	@Column(nullable = false, length = 10)
	private Integer dorsal;

	@Lob // tipo de datos grande
	@Column(nullable = true)
	private Byte[] foto;

	@Column(nullable = false, length = 255)
	private String pais;

	@Column(nullable = true, length = 255)
	private String twitter;

	@ManyToMany
	@JoinTable(name = "votaciones_emitidas", joinColumns = {
			@JoinColumn(name = "piloto_id", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "votacion_id", referencedColumnName = "id")
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

	@OneToOne
	@JoinColumn(name = "coche_id", referencedColumnName = "id")
	@JsonIgnoreProperties("piloto")
	private Coche coche;

	// OTROS METODOS

	public void addVotacion(Votacion votacion) {
		if (votacion != null) {
			getVotacion().add(votacion);
		}
	}

	public void removeVotacion(Votacion votacion) {
		if (votacion != null) {
			getVotacion().remove(votacion);
		}
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public List<Votacion> getVotacion() {
		return votacion;
	}

	public void setVotacion(List<Votacion> votacion) {
		this.votacion = votacion;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getDataurlb64() {
		return dataurlb64;
	}

	public void setDataurlb64(String dataurlb64) {
		this.dataurlb64 = dataurlb64;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	
	
}
