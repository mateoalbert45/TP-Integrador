package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Vuelo;

public interface VueloRepository extends JpaRepository<Vuelo, Long>{
	   @Query("select p.vuelo FROM ViajePlan vp join vp.viaje v join vp.plan p join v.usuario u where u.id=:idUsuario")
	    public List<Vuelo> vuelosSegunUsuario(Long idUsuario);
}
