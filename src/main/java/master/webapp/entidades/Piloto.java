package master.webapp.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
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
}
