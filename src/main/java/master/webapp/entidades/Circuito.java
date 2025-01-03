package master.webapp.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "circuito")
public class Circuito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,length=255)
	private String nombre;
	
	@Column(nullable = false,length=255)
	private String ciudad;
	
	@Column(nullable = false,length=255)
	private String pais;
	
	@Lob //tipo de datos grande
	@Column(nullable = true, columnDefinition="LONGTEXT")
	private String trazado;
	
	@Column(name = "numero_vueltas", nullable = false)
	private Integer numeroVueltas;
	private Float longitud;
	
	@Column(name = "curvas_lentas", nullable = false)
	private Integer curvasLentas;
	
	@Column(name = "curvas_media", nullable = false)
	private Integer curvasMedia;
	
	@Column(name = "curvas_rapidas", nullable = false)
	private Integer curvasRapidas;
	
	@Column(name = "fecha", nullable = true)
	private LocalDate fecha;
	//----------------------
	//Se generan los Getters
	//----------------------
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getPais() {
		return pais;
	}
	public String getTrazado() {
		return trazado;
	}
	public int getNumeroVueltas() {
		return numeroVueltas;
	}
	public Float getLongitud() {
		return longitud;
	}
	public int getCurvasLentas() {
		return curvasLentas;
	}
	public int getCurvasMedia() {
		return curvasMedia;
	}
	public int getCurvasRapidas() {
		return curvasRapidas;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	
	
	
	//----------------------
	//Se generan los Setters
	//----------------------
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setTrazado(String trazado) {
		this.trazado = trazado;
	}
	public void setNumeroVueltas(int numero_vueltas) {
		this.numeroVueltas = numero_vueltas;
	}
	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
	public void setCurvasLentas(int curvas_lentas) {
		this.curvasLentas = curvas_lentas;
	}
	public void setCurvasMedia(int curvas_media) {
		this.curvasMedia = curvas_media;
	}
	public void setCurvasRapidas(int curvas_rapidas) {
		this.curvasRapidas = curvas_rapidas;
	}	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
