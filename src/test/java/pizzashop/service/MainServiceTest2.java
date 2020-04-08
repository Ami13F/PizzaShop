package pizzashop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.MenuItem;
import pizzashop.repository.IMenuRepository;
import pizzashop.repository.IPaymentRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class MainServiceTest2 {

    private static IMenuRepository menuRepository;
    private static IPaymentRepository paymentRepository;
    private static MainService mainService;

    @BeforeAll
    static void init(){
        menuRepository = Mockito.mock(IMenuRepository.class);
        paymentRepository = Mockito.mock(IPaymentRepository.class);
        mainService = new MainService(menuRepository,paymentRepository);
    }

    @Test
    public void getMenuData_TC1(){
        Assertions.assertEquals(mainService.getMenuData().size(),0);
    }
    @Test
    public void getMenuData_TC2(){
        Mockito.when(menuRepository.getMenu()).thenReturn(new ArrayList<MenuItem>(
                Arrays.asList(new MenuItem("Hawaii", 2, 30.0),
                        new MenuItem("Hawaii", 2, 30.0))));
        Assertions.assertEquals(mainService.getMenuData().size(),2);
    }
}
