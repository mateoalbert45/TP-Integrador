package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import java.util.List;
import demo.controller.ViajeController;
import demo.model.Viaje;
import demo.repository.ViajeRepository;

public class viajeTest2 {
	static ViajeController viajeController;
	
	@BeforeClass
	public static void beforeClass() {
		ViajeController viajeController = new ViajeController(ViajeRepository repository);
	}

	@Test
	public void test() {
		int count = 0;
		Iterable<Viaje> caca = viajeController.getViajes();
		for (Viaje v : caca) {
			count++;
		}
		assertEquals(1, count);
	}
}

