package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Plan;
import demo.model.PlanVuelo;
import demo.model.Viaje;

public interface PlanRepository extends JpaRepository<Plan, Long> {

	   @Query("select p from PlanVuelo p")
	    public List<PlanVuelo> getPlanesVuelo();
	
}
