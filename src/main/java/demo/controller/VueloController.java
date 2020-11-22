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

import demo.model.Viaje;
import demo.model.Vuelo;
import demo.repository.ViajeRepository;
import demo.repository.VueloRepository;

@RestController
@RequestMapping("vuelo")

/**
* Esta clase administra los servicios rest de los clientes.
* @author Grupo4
* @version Octubre 21, 2020
*/

public class VueloController {
	 	@Qualifier("vueloRepository")
	    @Autowired
	    private final VueloRepository repository;

	
		public VueloController(@Qualifier("vueloRepository") VueloRepository repository) {
			this.repository = repository;
		}
	

	
	    @GetMapping("/getAll")
	    public Iterable<Vuelo> getVuelos() {
	        return repository.findAll();
	    }
	    
	    @PostMapping("/add")
	    public Vuelo addVuelo(@RequestBody Vuelo v) {
	        return repository.save(v);
	    }
	    
		 
		@DeleteMapping("/delete/{id}")
		 void delete(@PathVariable Long id) {
		        repository.deleteById(id);
		    }
		
	    @GetMapping("/getVuelosSegunUsuario/{idUsuario}")
	    public Iterable<Vuelo> getVuelosSegunUsario(@PathVariable Long idUsuario) {
	        return repository.vuelosSegunUsuario(idUsuario);
	    }

}
