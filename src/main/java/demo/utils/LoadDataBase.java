package demo.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.repository.ViajeRepository;


@Configuration
@Slf4j
class LoadDatabase {


	
	@Bean
    CommandLineRunner initDatabaseProducto(
    		@Qualifier("viajeRepository") ViajeRepository repositoryViaje) {
		 return args -> {
		 	};
			}
	}
            
        
    
    
		 
	 


