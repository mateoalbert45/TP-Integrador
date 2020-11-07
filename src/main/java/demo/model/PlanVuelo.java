package demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public class PlanVuelo extends Plan{
	@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idVuelo", referencedColumnName = "id")
	private Vuelo vuelo;
	
	private String prueba;
		
	public PlanVuelo() {}
	
	public PlanVuelo(Long id, String descripcion,Vuelo vuelo) {
		super(id,descripcion);
		this.vuelo = vuelo;
		this.prueba = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
	}



	public Vuelo getVuelo() {
		return vuelo;
	}
	

	public void setVuelo(Vuelo v) {
		this.vuelo = v;
	}
	
	
	public Plan deolver() {
		return this;
	}

	@Override
	public String toString() {
		return "PlanVuelo [vuelo=" + vuelo + ", prueba=" + prueba + "]";
	}
	
	
	
	
	
	
}
