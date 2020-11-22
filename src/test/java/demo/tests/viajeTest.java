package demo.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import demo.controller.ViajeController;
import demo.model.Viaje;
import demo.repository.ViajeRepository;

public class viajeTest {
	static ViajeController viajeController;

	@BeforeClass
	public static void beforeClass() {
		ViajeRepository repository = null;
		ViajeController viajeController = new ViajeController(repository);
	}

	@Test
	public void test() {
		int count = 0;
		Iterable<Viaje> caca = viajeController.getViajes();
		for (Viaje v : caca) {
			count++;
		}
		assertEquals(3, count);
	}
}
