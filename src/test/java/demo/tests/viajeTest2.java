package demo.tests;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import javax.ws.rs.core.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.controller.ViajeController;
import demo.model.Viaje;
import demo.repository.ViajeRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ViajeController.class)
@WithMockUser
@EnableJpaRepositories("demo.repository.ViajeRepository")

public class viajeTest2{
	@MockBean
	ViajeRepository viajeRepository;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void createViaje() throws Exception {
		System.out.println("dasdasdasdasds");
	 	LocalDate fecha3 = LocalDate.of(2020, 10, 10);
	 	LocalDate fecha4 = LocalDate.of(2020, 10, 11);
		Viaje viaje = new Viaje(Long.valueOf("8"), "viajeA", fecha3, fecha4, "viajeA");
		when(viajeRepository.save(viaje));
		((MockHttpServletRequestBuilder) mockMvc.perform(post("/viaje/add")))
			.content(mapper.writeValueAsString(viaje))
			.contentType(MediaType.APPLICATION_JSON);
	}
}