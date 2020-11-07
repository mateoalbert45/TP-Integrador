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
	
					


}
