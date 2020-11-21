package tests;


import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import demo.controller.ViajeController;
import demo.model.Viaje;
import demo.repository.ViajeRepository;

public class viajeTest2{


    private ViajeRepository viajeRepository = Mockito.mock(ViajeRepository.class);
    private ViajeController viajeController = new ViajeController(viajeRepository);


    @BeforeClass
    public static void beforeClass() {
        //ViajeRepository repository = null;
    //    ViajeController viajeController = new ViajeController(repository);
    }


    @Test
    public void test() {
        int count = 0;
        Iterable<Viaje> caca =  viajeController.getViajes();
        for (Viaje v : caca) {
            count++;
        }
//        System.out.println(caca.getId());
        System.out.println(count);
        assertEquals(3, count);
    }

}