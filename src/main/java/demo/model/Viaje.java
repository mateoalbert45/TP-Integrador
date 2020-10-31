package demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Viaje {
	@Id
	private Long id;
	@Column
	private String nombre;
	@Column
	private String destinos;
	@Column
	private String fechaInicio;
	@Column
	private String fechaFin;
	@Column
	private String descripcion;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
	private List <ViajePlan> planes;
	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
	private Usuario usuario;
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idVuelo", referencedColumnName = "id")
	private List<Vuelo> vuelos;
	
	public Viaje() {}

	
	public Viaje(Long id, String nombre, String fechaInicio, String fechaFin, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
	}
	
	
	public void addVuelo(Vuelo v) {
		vuelos.add(v);
	}


	public void addPlan(Plan p, Long idViaje) {
		ViajePlanPK pk = new ViajePlanPK(idViaje, p.getId());
		ViajePlan vp = new ViajePlan(pk, this , p);
		planes.add(vp);
	}
	
	
	
	
}
