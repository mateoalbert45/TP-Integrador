package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Plan;
import demo.model.PlanVuelo;
import demo.model.Viaje;
import demo.model.Vuelo;
import demo.repository.PlanRepository;
import demo.repository.ViajeRepository;

@RestController
@RequestMapping("plan")
/**
 * Esta clase administra los servicios rest de los clientes.
 * 
 * @author Grupo4
 * @version Octubre 21, 2020
 */
public class PlanController {
	@Qualifier("planRepository")
	@Autowired
	private final PlanRepository repository;

	public PlanController(@Qualifier("planRepository") PlanRepository repository) {
		this.repository = repository;
	}

    @GetMapping("/getAll")
    public Iterable<Plan> getPlan() {
        return repository.findAll();
    }
    
    @PostMapping("/add")
    public Plan newPlan(@RequestBody Plan p) {
        return repository.save(p);
    }
    
    
    @PostMapping("/addPlanVuelo/{idPlan}/{descripcionPlan}")
    public PlanVuelo newPlan(@RequestBody Vuelo v,@PathVariable long idPlan,@PathVariable String descripcionPlan) {
    	PlanVuelo p = new PlanVuelo(idPlan, descripcionPlan,v);
    	//p.setVuelo();
    	System.out.println(p.getVuelo().getCompañia());
    	System.out.println(p.getVuelo().getAeropuertoLlegada());
        return repository.save(p);
    }
    
	@DeleteMapping("/delete/{id}")
	 void deletePlan(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	
	
	
    
}
