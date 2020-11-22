package tests;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.controller.ViajeController;
import demo.model.Viaje;
import demo.repository.ViajeRepository;
import io.swagger.v3.oas.models.media.MediaType;

@RunWith(SpringRunner.class)
@WebMvcTest(ViajeController.class)
public class viajeTest2{
	@MockBean
	ViajeRepository viajeRepository;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void createViaje() throws Exception {
	 	LocalDate fecha3 = LocalDate.of(2020, 10, 10);
	 	LocalDate fecha4 = LocalDate.of(2020, 10, 11);
		Viaje viaje = new Viaje(Long.valueOf("1"), "viajeA", fecha3, fecha4, "viajeA");
		when(viajeRepository.save(viaje));
		((MockHttpServletRequestBuilder) mockMvc.perform(post("http://localhost:8080/viaje/add")))
			.content(mapper.writeValueAsString(viaje))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpected(status().isOk()));
	}
}