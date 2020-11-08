package demo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service {
	//Servicio público. No requiere login. Está configurado en LoginConfiguration
	@GetMapping("/greetings")
	public String helloWorld() {
		return "Hello World"; 
	}
	//Servicio que requiere estar logeados (por defecto).
	@GetMapping("/loginrequired")
	public String logged() {
		return "Login required: " + SecurityContextHolder.getContext(); 
	}
	
	
	//Servicio que requiere la autoridad de LINK
	@PreAuthorize("hasAuthority('LINK')")
	@GetMapping("/oldman")
	public String oldman() {
		return "It's dangerous to go alone! Take this. Login info: " + SecurityContextHolder.getContext(); 
	}
}
