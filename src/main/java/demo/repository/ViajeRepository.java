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
	   @Query("select v.usuario from Viaje v join v.usuario u group by v.usuario order by count(v) DESC")
	    public List<Usuario> getUsuarioMasViajes();
	   @Query("select d from Viaje v inner join v.destinos d group by d order by count(v) DESC")
	   	public List<String> getViajesPorZona();
	   
}
