package master.webapp.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coche")
public class Coche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String codigo;

	@Column(nullable = false)
	private Float consumo;

	@Column(name = "ers_curva_lenta", nullable = false)
	private Float ersCurvaLenta;

	@Column(name = "ers_curva_media", nullable = false)
	private Float ersCurvaMedia;

	@Column(name = "ers_curva_rapida", nullable = false)
	private Float erdCurvaRapidas;

	@OneToOne
	@JoinColumn(name = "piloto_id", referencedColumnName = "id")
	@JsonIgnoreProperties("coche")
	private Piloto piloto;

}
