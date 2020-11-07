package demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Viaje {
	@Id
	private Long id;
	@Column
	private String nombre;
	@ElementCollection
	@CollectionTable(name="destinos", joinColumns=@JoinColumn(name="destino_id"))
	@Column(name="destinos")
	private List<String> destinos;
	@Column
	private LocalDate fechaInicio;
	@Column
	private LocalDate fechaFin;
	@Column
	private String descripcion;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
	
	private List <ViajePlan> planes;
	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
	private Usuario usuario;
	
	public Viaje() {}

	
	public Viaje(Long id, String nombre,  LocalDate fechaInicio, LocalDate fechaFin, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
	}
	
	
	
	public void addDestino(String d) {
		destinos.add(d);
	}
	

	
	
	
	
}
