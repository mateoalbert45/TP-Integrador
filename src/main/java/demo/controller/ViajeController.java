package demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import demo.model.Usuario;
import demo.model.Viaje;
import demo.model.ViajePlan;
import demo.model.ViajePlanPK;
import demo.model.Vuelo;
import demo.repository.ViajeRepository;

@RestController
@RequestMapping("viaje")

/**
* Esta clase administra los servicios rest de los clientes.
* @author Grupo4
* @version Octubre 21, 2020
*/

public class ViajeController {
	 	@Qualifier("viajeRepository")
	    @Autowired
	    private final ViajeRepository repository;

	
		public ViajeController(@Qualifier("viajeRepository") ViajeRepository repository) {
			this.repository = repository;
		}
	
	
	    @GetMapping("/getAll")
	    public Iterable<Viaje> getViajes() {
	        return repository.findAll();
	    }
	    
	
	    @PostMapping("/add")
	    public Viaje newViaje(@RequestBody Viaje v) {
	        return repository.save(v);
	    }
	    
	    @PostMapping("/asignarUsuario/{idViaje}/{idUsuario}")
	    public Viaje asignarUsuario(@PathVariable Long idViaje,@PathVariable Long idUsuario) {
	    	Usuario usuario = repository.getUsuario(idUsuario);
	        return repository.findById(idViaje)
          .map(viaje -> {
          	viaje.setUsuario(usuario);
              return repository.save(viaje);
          })
          .orElseGet(() -> {
              return null;
          });
	    }

		@DeleteMapping("/delete/{id}")
		 void deleteViaje(@PathVariable Long id) {
		        repository.deleteById(id);
		    }
		
	    @GetMapping("/usuarioMasViajes")
	    public Usuario usuarioMasViajes() {
	        return repository.getUsuarioMasViajes().get(0);
	    }
	    
	    @GetMapping("/masViajesPorZona")
	    public Object masViajesPorZona() {
	        return JSONObject.quote(repository.getViajesPorZona().get(0));
	    }
		
		
//	    @PostMapping("/asignarVuelo/{idViaje}")
//	    public Viaje asignarVuelo(@RequestBody Vuelo vuelo, @PathVariable Long idViaje) {
//	        return repository.findById(idViaje)
//            .map(viaje -> {
//            	viaje.addVuelo(vuelo);
//                return repository.save(viaje);
//            })
//            .orElseGet(() -> {
//                return null;
//            });
//	    
//	    }


	    
	    
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
