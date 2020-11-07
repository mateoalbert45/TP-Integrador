package demo.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.model.Plan;
import demo.model.PlanVuelo;
import demo.model.Usuario;
import demo.model.Viaje;
import demo.model.ViajePlan;
import demo.model.ViajePlanPK;
import demo.model.Vuelo;
import demo.repository.PlanRepository;
import demo.repository.UsuarioRepository;
import demo.repository.ViajePlanRepository;
import demo.repository.ViajeRepository;
import demo.repository.VueloRepository;


@Configuration
@Slf4j
class LoadDatabase {


	
	@Bean
    CommandLineRunner initDatabaseProducto(
    		@Qualifier("usuarioRepository") UsuarioRepository repositoryUsuario,
    		@Qualifier("planRepository") PlanRepository repositoryPlan,
    		@Qualifier("vueloRepository") VueloRepository repositoryVuelo,
    		@Qualifier("viajeRepository") ViajeRepository repositoryViaje,
    		@Qualifier("viajePlanRepository") ViajePlanRepository repositoryViajePlan) {
		 return args -> {
			 	
			 	LocalDate fecha1 = LocalDate.of(2025, 4, 12);
			 	LocalDate fecha2 = LocalDate.of(2025, 5, 12);
			 	LocalDate fecha3 = LocalDate.of(2025, 10, 12);
			 	LocalDate fecha4 = LocalDate.of(2025, 10, 13);
			 	
		    	Usuario u1 = new Usuario(Long.valueOf("1"), "a","a", "a");
		    	Vuelo v1 = new Vuelo(Long.valueOf("1"), "c", "c", "c", "c", "c", Long.valueOf("1"), Long.valueOf("1"), "c");
		    	Plan p1 = new PlanVuelo(Long.valueOf("1"), "a", v1);
		    	Viaje v = new Viaje(Long.valueOf("1"), "asd", "chile-argentina-porongol", fecha1, fecha2, "xd");
		    	Viaje viaje1 = new Viaje(Long.valueOf("2"), "viaje", "chile-argentina-porongol", fecha3, fecha4, "xd");
		    	
		    	v.setUsuario(u1);
		    	viaje1.setUsuario(u1);
		    	

	            log.info("Preloading " + repositoryUsuario.save(u1));
	            log.info("Preloading " + repositoryViaje.save(v));
	            log.info("Preloading " + repositoryPlan.save(p1));
	            log.info("Preloading " + repositoryViaje.save(viaje1));
//	            log.info("Preloading " + repositoryPlan.save(p1));


		 	};
			}
	}
            
        
    
    
		 
	 


