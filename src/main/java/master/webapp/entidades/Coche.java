package master.webapp.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getConsumo() {
		return consumo;
	}

	public void setConsumo(Float consumo) {
		this.consumo = consumo;
	}

	public Float getErsCurvaLenta() {
		return ersCurvaLenta;
	}

	public void setErsCurvaLenta(Float ersCurvaLenta) {
		this.ersCurvaLenta = ersCurvaLenta;
	}

	public Float getErsCurvaMedia() {
		return ersCurvaMedia;
	}

	public void setErsCurvaMedia(Float ersCurvaMedia) {
		this.ersCurvaMedia = ersCurvaMedia;
	}

	public Float getErdCurvaRapidas() {
		return erdCurvaRapidas;
	}

	public void setErdCurvaRapidas(Float erdCurvaRapidas) {
		this.erdCurvaRapidas = erdCurvaRapidas;
	}

}
