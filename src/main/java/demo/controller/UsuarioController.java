package demo.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

	@GetMapping("/viajesPendientes/{idUsuario}")
	public List<Viaje> viajesPendientes(@PathVariable Long idUsuario) throws ParseException {
		List<Viaje> viajes = repository.getViajes(idUsuario);
		List<Viaje> viajesPendientes = new ArrayList<Viaje>();
		if (viajes.size() != 0) {
			for (Viaje v : viajes) {
				if (v.getFechaFin().getYear() > LocalDate.now().getYear()) {
					viajesPendientes.add(v);
				} else {
					if (v.getFechaFin().getYear() == LocalDate.now().getYear()
							&& v.getFechaFin().getMonthValue() > LocalDate.now().getMonthValue()) {
						viajesPendientes.add(v);
					} else {
						if (v.getFechaFin().getYear() == LocalDate.now().getYear()
								&& v.getFechaFin().getMonthValue() == LocalDate.now().getMonthValue()
								&& v.getFechaFin().getDayOfMonth() > LocalDate.now().getDayOfMonth())
							viajesPendientes.add(v);
					}
				}
			}
		}
		return viajesPendientes;

	}

	@GetMapping("/viajesFinalizados/{idUsuario}")
	public List<Viaje> viajesFinalizados(@PathVariable Long idUsuario) throws ParseException {
		List<Viaje> viajes = repository.getViajes(idUsuario);
		List<Viaje> viajesFinalizados = new ArrayList<Viaje>();
		if (viajes.size() != 0) {
			for (Viaje v : viajes) {
				if (v.getFechaFin().getYear() < LocalDate.now().getYear()) {
					viajesFinalizados.add(v);
				} else {
					if (v.getFechaFin().getYear() == LocalDate.now().getYear()
							&& v.getFechaFin().getMonthValue() < LocalDate.now().getMonthValue()) {
						viajesFinalizados.add(v);
					} else {
						if (v.getFechaFin().getYear() == LocalDate.now().getYear()
								&& v.getFechaFin().getMonthValue() == LocalDate.now().getMonthValue()
								&& v.getFechaFin().getDayOfMonth() < LocalDate.now().getDayOfMonth())
							viajesFinalizados.add(v);
					}
				}
			}
		}
		return viajesFinalizados;
	}

	@GetMapping("/viajesRangoFecha/{idUsuario}/{fecha1}/{fecha2}")
	public List<Viaje> viajesRangoFecha(@PathVariable Long idUsuario, @PathVariable String fecha1, @PathVariable String fecha2 ) throws ParseException {
		LocalDate fechaPrincipio = LocalDate.parse(fecha1);
		LocalDate fechaFin = LocalDate.parse(fecha2);
		List<Viaje> viajes = repository.getViajes(idUsuario);
		List<Viaje> viajesRangoFecha = new ArrayList<Viaje>();
		if(viajes.size() != 0) {
			for(Viaje v: viajes) {
				System.out.println(v.getFechaInicio().compareTo(fechaPrincipio));
			    if((v.getFechaInicio().compareTo(fechaPrincipio) >= 1)&&(v.getFechaFin().compareTo(fechaFin) <= -1)) {
			    	viajesRangoFecha.add(v);
			    }
			    }
			}
		return viajesRangoFecha;

		}
    @GetMapping("/viajesPorZona/{usuario}/{destino}")
    public List<Viaje> viajesPorZona(@PathVariable Long usuario, @PathVariable String destino) {
    	List<Viaje> viajes = repository.getViajes(usuario);
    	List<Viaje> viajesPorZona = new ArrayList<Viaje>();

    	for(Viaje v: viajes) {
    		System.out.println("destinos  " + v.getDestinos());
    		System.out.println("Destino "  +destino);
    		if(v.getDestinos().contains(destino)) {
    			viajesPorZona.add(v);
    		}
    	}
        return viajesPorZona;

    }
	
    
	
	
	
    
}
