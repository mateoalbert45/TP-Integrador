package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Plan;
import demo.model.Usuario;
import demo.model.Viaje;
import demo.model.ViajePlan;

public interface ViajePlanRepository extends JpaRepository<ViajePlan, Long> {
	
	
	   @Query("select v FROM Viaje v where id=:idViaje")
	    public Viaje getViaje(long idViaje);
}
