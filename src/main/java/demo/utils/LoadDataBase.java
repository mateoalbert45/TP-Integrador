package demo.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		    	Usuario u1 = new Usuario(Long.valueOf("1"), "a","a", "a");
		    	Vuelo vu1 = new Vuelo(Long.valueOf("1"), "c", "c", "c", "c", "c", Long.valueOf("1"), Long.valueOf("1"), "c");
		    	Plan p1 = new PlanVuelo(Long.valueOf("1"), "a", vu1);
		    	Viaje vi1 = new Viaje(Long.valueOf("1"), "viaje", "12/10/2025", "12/11/2025", "xd");
		    	ViajePlanPK pk = new ViajePlanPK(Long.valueOf("1"), Long.valueOf("1"));
				ViajePlan vp = new ViajePlan(pk, vi1 , p1);

		    	vi1.setUsuario(u1);

	            log.info("Preloading " + repositoryUsuario.save(u1));
//	            log.info("Preloading " + repositoryViaje.save(vi1));
//	            log.info("Preloading " + repositoryPlan.save(p1));


		 	};
			}
	}
            
        
    
    
		 
	 


