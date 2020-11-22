package demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Plan;
import demo.model.PlanVuelo;
import demo.model.Viaje;
import demo.model.ViajePlan;
import demo.model.ViajePlanPK;
import demo.model.Vuelo;
import demo.repository.ViajePlanRepository;
import demo.repository.ViajeRepository;

@RestController
@RequestMapping("viajePlan")

/**
* Esta clase administra los servicios rest de los clientes.
* @author Grupo4
* @version Octubre 21, 2020
*/

public class ViajePlanController {
	 	@Qualifier("viajePlanRepository")
	    @Autowired
	    private final ViajePlanRepository repository;

	
		public ViajePlanController(@Qualifier("viajePlanRepository") ViajePlanRepository repository) {
			this.repository = repository;
		}
	
	
	    @GetMapping("/getAll")
	    public Iterable<ViajePlan> getViajePlane() {
	        return repository.findAll();
	    }
	    
	
	    @PostMapping("/add")
	    public ViajePlan newViajePlan(@RequestBody ViajePlan vp) {
	        return repository.save(vp);
	    }

		@DeleteMapping("/delete/{id}")
		 void deleteViajePlan(@PathVariable Long id) {
		        repository.deleteById(id);
		    }

	    @PostMapping("/asignarPlanViaje/{idViaje}/{idPlan}")
	    public ViajePlan asignarPlan(@PathVariable Long idPlan, @PathVariable Long idViaje) {
			ViajePlanPK pk = new ViajePlanPK(idViaje, idPlan);
			Viaje viaje = repository.getViaje(idViaje);
			Plan plan = repository.getPlan(idPlan);
			ViajePlan vp = new ViajePlan(pk, viaje , plan);
			return repository.save(vp);
	    }
	    
	    
	    @GetMapping("/planSegunViaje/{idViaje}")
	    public Object planSegunViaje(@PathVariable Long idViaje) {
	    	String planes = "";
	    	Plan po = repository.planSegunViaje(idViaje).get(0);
    		System.out.println("de aca");

    		System.out.println(po.getId());
    		System.out.println(po.toString());
    		System.out.println("hasta aca");

	    	for(Plan p: repository.planSegunViaje(idViaje)) {
	    		System.out.println(p.toString());
	    	planes += p.toString() + "  ";
	    	}
	        return JSONObject.quote(planes);
    }
	    
//	    @GetMapping("/planSegunViaje/{idViaje}")
//	    public String planSegunViaje(@PathVariable Long idViaje) {
//	    	List<Plan> planes = new ArrayList<Plan>();
//	    	
//    	for(Plan p: repository.planSegunViaje(idViaje)) {
//	    		planes.add(p);
//	    	}
//    	
//	        return new JSONArray(planes).toString();
//    }
    


}
