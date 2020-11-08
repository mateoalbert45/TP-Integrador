package demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Hotel {
	@Id
	private Long id;
	@Column
	private String nombre;
	@Column
	private String ciudad;
	@Column
	private int cantEstrellas;
	@Column
	private String fechaLlegada;
	@Column
	private String fechaSalida;
	//fijate que tiene que ser formato fecha y hora

	
	
	public Hotel() {
		
	}


	public Hotel(Long id, String nombre, String ciudad, int cantEstrellas, String fechaSalida, String fechaLlegada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.cantEstrellas = cantEstrellas;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
	}



	

}
