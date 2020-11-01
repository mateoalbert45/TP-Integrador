package demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	    
//		 @PutMapping("/update/{id}") public Cliente updateCliente(@RequestBody Cliente c, @PathVariable Long id) {
//		        return repository.findById(id)
//		                .map(cliente -> {
//		                	cliente.setNombre(c.getNombre());
//		                    return repository.save(cliente);
//		                })
//		                .orElseGet(() -> {
//		                    c.setId(id);
//		                    return repository.save(c);
//		                });
//		 }
		 

		
		

//	    @GetMapping("/reporteCompras")
//	    public List<ReporteGastosCliente> getReporteCompras() {
//	    	List<ReporteGastosCliente> reportes = new ArrayList<>();
//	    	List<Cliente> clientes = repository.findAll();
//	    	for(Cliente c : clientes) {
//	    		ReporteGastosCliente reporte = new ReporteGastosCliente();
//	    		reporte.setCliente(c);
//	    		int gastos = repository.gastosSegunCliente(c.getId());
//	    		reporte.setGastos(gastos);
//	    		reportes.add(reporte);
//	    	}
//	        return reportes;
//	    }
	    

//			@PostMapping("/comprar/{idCompra}/{idCliente}")
//			public ResponseEntity compraCliente(@PathVariable Long  idCompra,@PathVariable Long idCliente) {
//				
//				Optional<Cliente> cliente = repository.findById(idCliente);
//				Compra c = repository.getCompra(idCompra);
//				System.out.println(c.getId());
//				cliente.get().add(c);
//				repository.save(cliente.get());
//				List<Producto> productos = repository.getProductosSegunCompra(c.getId());
//				System.out.println(productos);
//				for(Producto p: productos) {
//					System.out.println("cantidad para producto"+ p.getNombre() + repository.ventasProducto(p.getId(),c.getFechaDeCompra(),idCliente));
//					if(repository.ventasProducto(p.getId(),c.getFechaDeCompra(),idCliente)>3) {
//							cliente.get().remove(c);
//							repository.save(cliente.get());
//							return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Limite de productos al dia superado");
//					}
//				}
//				
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra realizada");
//			}
					


}
