package demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class  Plan {
	@Id
	private Long id;
	@Column
	private String descripcion;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
	private List <ViajePlan> viajes;
	
	
	
	public Plan() {}



	public Plan(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	
	public Plan deolver() {
		return this;
		
	}



	@Override
	public String toString() {
		return "Plan [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
	
}
