package demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public class PlanHotel extends Plan{
	@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "idHotel", referencedColumnName = "id")
	private Hotel hotel;
	
	public PlanHotel() {}


	public PlanHotel(Long id, String descripcion,Hotel hotel) {
		super(id,descripcion);
		this.hotel = hotel;
	}


	public Hotel getHotel() {
		return hotel;
	}

	@Override
	public String toString() {
		return "PlanHotel [hotel=" + hotel + "]";
	}

	
	
	
	
}
