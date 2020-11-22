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

import demo.model.Hotel;
import demo.model.Plan;
import demo.model.PlanHotel;
import demo.model.PlanVuelo;
import demo.model.Usuario;
import demo.model.Viaje;
import demo.model.ViajePlan;
import demo.model.ViajePlanPK;
import demo.model.Vuelo;
import demo.repository.HotelRepository;
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
    		@Qualifier("viajePlanRepository") ViajePlanRepository repositoryViajePlan,
    		@Qualifier("hotelRepository") HotelRepository repositoryHotel){
		 return args -> {
			 	LocalDate fecha1 = LocalDate.of(2020, 9, 10);
			 	LocalDate fecha2 = LocalDate.of(2020, 9, 11);
			 	LocalDate fecha3 = LocalDate.of(2020, 10, 10);
			 	LocalDate fecha4 = LocalDate.of(2020, 10, 11);
			
		    	Usuario u1 = new Usuario(Long.valueOf("1"), "a","a", "a");
		    	Usuario u2 = new Usuario(Long.valueOf("2"), "b","b", "b");

		    	Vuelo vu1 = new Vuelo(Long.valueOf("1"), "vueloA","10/9/2020", "11/9/2020", "vueloA", "vueloA", Long.valueOf("1"), Long.valueOf("1"), "vueloA");
		    	
		    	Hotel h1 = new Hotel(Long.valueOf("1"), "hotelA", "hotelA", 5, "10/10/2020", "11/10/2020");

		    	Plan p1 = new Plan(Long.valueOf("1"), "PlanA");
		    	Plan p2=  new PlanVuelo(Long.valueOf("2"), "PlanB", vu1);
		    	Plan p3 = new PlanHotel(Long.valueOf("3"), "PlanC", h1);

		    	
		    	Viaje viaje1 = new Viaje(Long.valueOf("1"), "viajeA", fecha3, fecha4, "viajeA");
		    	viaje1.addDestino("ARG");
		    	viaje1.addDestino("CHI");
		    	viaje1.setUsuario(u1);

		    	Viaje viaje2 = new Viaje(Long.valueOf("2"), "viajeB", fecha1, fecha2, "viajeB");
		    	viaje2.addDestino("CHI");
		    	viaje2.setUsuario(u1);

		    	Viaje viaje3 = new Viaje(Long.valueOf("3"), "viajeC", fecha3, fecha4, "viajeC");
		    	viaje3.addDestino("CHI");
		    	viaje3.addDestino("URU");
		    	viaje3.setUsuario(u2);

				ViajePlanPK pK1 = new ViajePlanPK(Long.valueOf("1"), Long.valueOf("1"));
				ViajePlanPK pk2 = new ViajePlanPK(Long.valueOf("2"), Long.valueOf("2"));
				ViajePlanPK pk3 = new ViajePlanPK(Long.valueOf("3"), Long.valueOf("3"));

				ViajePlan vp1 = new ViajePlan(pK1, viaje1 , p1);
				ViajePlan vp2 = new ViajePlan(pk2, viaje2 , p2);
				ViajePlan vp3 = new ViajePlan(pk3, viaje3 , p3);

				
		    	log.info("Preloading " + repositoryUsuario.save(u1));
		    	log.info("Preloading " + repositoryUsuario.save(u2));
		    	
		    	log.info("Preloading " + repositoryVuelo.save(vu1));
		    	
		    	log.info("Preloading " + repositoryHotel.save(h1));
		    	
	            log.info("Preloading " + repositoryPlan.save(p1));
	            log.info("Preloading " + repositoryPlan.save(p2));
	            log.info("Preloading " + repositoryPlan.save(p3));

	            log.info("Preloading " + repositoryViaje.save(viaje1));
	            log.info("Preloading " + repositoryViaje.save(viaje2));
	            log.info("Preloading " + repositoryViaje.save(viaje3));

	            log.info("Preloading " + repositoryViajePlan.save(vp1).getClass());
	            log.info("Preloading " + repositoryViajePlan.save(vp2).getClass());
	            log.info("Preloading " + repositoryViajePlan.save(vp3).getClass());

	            
		 	};
			}
	
	}
            
        
    
    
		 
	 


