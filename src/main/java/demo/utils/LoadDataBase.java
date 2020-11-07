package demo.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.Session;
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

@Transactional
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
			
		    	Usuario u1 = new Usuario(Long.valueOf("1"), "usuario","masviajes", "a");
		    	Usuario u2 = new Usuario(Long.valueOf("2"), "usuario","menosviajes", "a");

		    	Vuelo vu1 = new Vuelo(Long.valueOf("1"), "c", "c", "c", "c", "c", Long.valueOf("1"), Long.valueOf("1"), "c");
		    	
		    	Plan p1 = new PlanVuelo(Long.valueOf("1"), "a", vu1);
		    	
		    	Viaje viaje1 = new Viaje(Long.valueOf("1"), "asd", fecha1, fecha2, "xd");
		    	viaje1.addDestino("arg");

		    	Viaje viaje2 = new Viaje(Long.valueOf("2"), "viaje", fecha3, fecha4, "xd");
		    	viaje2.addDestino("arg");

		    	Viaje viaje3 = new Viaje(Long.valueOf("3"), "viaje", fecha3, fecha4, "xd");
		    	viaje3.addDestino("chi");


		    	
		    	viaje1.setUsuario(u1);
		    	viaje2.setUsuario(u1);

//				ViajePlanPK pk = new ViajePlanPK(Long.valueOf("1"), Long.valueOf("1"));
//				ViajePlan vp = new ViajePlan(pk, viaje1 , p1);
		    	
		    	
		    	
		    	log.info("Preloading " + repositoryUsuario.save(u1));
		    	 log.info("Preloading " + repositoryVuelo.save(vu1));
	            log.info("Preloading " + repositoryPlan.save(p1));
	            log.info("Preloading " + repositoryViaje.save(viaje1));
	            log.info("Preloading " + repositoryViaje.save(viaje2));
	            log.info("Preloading " + repositoryViaje.save(viaje3));

	           // log.info("Preloading " + repositoryViajePlan.save(vp));

	            
	            
	            //
		 	};
			}
	
	}
            
        
    
    
		 
	 


