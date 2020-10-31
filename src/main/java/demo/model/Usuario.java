package demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Usuario {
	@Id
	private Long id;
	@Column
	private String nombre;
	@Column
	private String contraseña;
	@Column
	private String mail;
	
	
	public Usuario() {}

	public Usuario(Long id, String nombre, String contraseña, String mail) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.mail = mail;
	}

	
	

}
