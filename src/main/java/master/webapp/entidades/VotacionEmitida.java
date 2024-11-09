package master.webapp.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="votaciones_emitidas",schema="formula_uno")
public class VotacionEmitida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="votacion_id",nullable=false,length=10)
	private Integer votacionId;
	
	@Column(name="nombre_publico",nullable=false,length=255)
	private String nombrePublico;
	
	@Column(nullable=false,length=255)
	private String email;
	
	//llave foránea contiene el id de la tabla piloto
	@Column(name="piloto_id",nullable=false,length=10)
	private Integer pilotoId;

	
	//----------------------
	//Se generan los Getters
	//----------------------
		
	public Integer getId() {
		return id;
	}
	public Integer getVotacionId() {
		return votacionId;
	}
	public String getNombrePublico() {
		return nombrePublico;
	}
	public String getEmail() {
		return email;
	}
	public Integer getPilotoId() {
		return pilotoId;
	}
	
	
	//----------------------
	//Se generan los Setters
	//----------------------
		
	public void setId(Integer id) {
		this.id = id;
	}
	public void setVotacionId(Integer votacionId) {
		this.votacionId = votacionId;
	}
	public void setNombrePublico(String nombrePublico) {
		this.nombrePublico = nombrePublico;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPilotoId(Integer pilotoId) {
		this.pilotoId = pilotoId;
	}

}
