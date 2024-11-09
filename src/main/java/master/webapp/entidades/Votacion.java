package master.webapp.entidades;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="votacion",schema="formula_uno")
public class Votacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false,length=255)//validacion a nivel de base de datos
	private String permalink;
	
	@Column(nullable=false,length=255)
	private String titulo;
	
	@Column(nullable=true,length=255)
	private String descripcion;
	
	@Column(nullable=false)
	private LocalDateTime limite;
	
	@Column(name="lista_pilotos", nullable=true, columnDefinition="TEXT")
	private String listaPilotos;
	
	@ManyToMany(mappedBy="votacion")
	@JsonIgnoreProperties("votacion")
	private List<Piloto> piloto;

	
	//----------------------
	//Se generan los Getters
	//----------------------
	
	public Integer getId() {
		return id;
	}
	public String getPermalink() {
		return permalink;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public LocalDateTime getLimite() {
		return limite;
	}
	public String getListaPilotos() {
		return listaPilotos;
	}
	public List<Piloto> getPiloto() {
		return piloto;
	}
		
	
	//----------------------
	//Se generan los Setters
	//----------------------
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setLimite(LocalDateTime limite) {
		this.limite = limite;
	}
	public void setListaPilotos(String listaPilotos) {
		this.listaPilotos = listaPilotos;
	}
	public void setPiloto(List<Piloto> piloto) {
		this.piloto = piloto;
	}
	
	
	//OTROS METODOS
	
	public void addPiloto(Piloto piloto) {
		if(piloto != null) {
			getPiloto().add(piloto);
		}
	}
	public void removePiloto(Piloto piloto) {
		if(piloto != null) {
			getPiloto().remove(piloto);
		}
	}
	
	
}
