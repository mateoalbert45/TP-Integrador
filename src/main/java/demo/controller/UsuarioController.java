package demo.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import demo.model.Usuario;
import demo.model.Viaje;
import demo.repository.PlanRepository;
import demo.repository.UsuarioRepository;
import demo.repository.ViajeRepository;

@RestController
@RequestMapping("usuario")
/**
 * Esta clase administra los servicios rest de los clientes.
 * 
 * @author Grupo4
 * @version Octubre 21, 2020
 */
public class UsuarioController {
	@Qualifier("usuarioRepository")
	@Autowired
	private final UsuarioRepository repository;

	public UsuarioController(@Qualifier("usuarioRepository") UsuarioRepository repository) {
		this.repository = repository;
	}

    @GetMapping("/getAll")
    public Iterable<Usuario> getUsuario() {
        return repository.findAll();
    }
    
    @PostMapping("/add")
    public Usuario newUsuario(@RequestBody Usuario u) {
        return repository.save(u);
    }
    
	@DeleteMapping("/delete/{id}")
	 void deleteUsuario(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	
    @GetMapping("/getId/{mail}/{contraseña}")
    public long getId(@PathVariable String mail,@PathVariable String contraseña) {
        return repository.getIdUsuario(mail,contraseña);
    }
    
	@GetMapping("/viajesPendientes")
	public List<Viaje> viajesPendientes() throws ParseException {
		List<Viaje> viajes = repository.getViajes(Long.valueOf("1"));
		List<Viaje> viajesPendientes = new ArrayList<Viaje>();
		if(viajes.size() != 0) {
			for(Viaje v: viajes) {
			    String fechaInicio= v.getFechaInicio();  
			    java.util.Date fechaParseada =  new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicio);  
			    if(fechaParseada.after(new java.sql.Date(Calendar.getInstance().getTime().getTime()))) {
			    	viajesPendientes.add(v);
			    }
			    	
			    }
			}
		return viajesPendientes;

		}
    
	
	
	
    
}
