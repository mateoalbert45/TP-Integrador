package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Plan;
import demo.model.Usuario;
import demo.model.Viaje;
import demo.model.Vuelo;

public interface ViajeRepository extends JpaRepository<Viaje, Long>{

	   @Query("select u FROM Usuario u where id=:idUsuario")
	    public Usuario getUsuario(long idUsuario);
	   
}
