package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.MenuItem;
import pizzashop.repository.IMenuRepository;
import pizzashop.repository.IPaymentRepository;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

public class MainServiceITest2 {
    private static IMenuRepository menuRepo;
    private static IPaymentRepository paymentRepository;

    private static MainService mainService;
    @BeforeAll
    static void init(){
        MainServiceITest2.menuRepo =
                new MenuRepository("/data/menu_test.csv");
        MainServiceITest2.paymentRepository =
                new PaymentRepository("/data/pay_test.csv");

        MainServiceITest2.mainService = new MainService(menuRepo, paymentRepository);
    }
    @AfterEach
    public void clear_list(){
        mainService.clear();
    }

    @Test
    public void getMenuData_TC3(){
        Assertions.assertTrue(mainService.getMenuData().isEmpty());
    }
    @Test
    public void getMenuData_TC4(){
        MenuItem menuItem1 =new MenuItem("Hawaii",2,  30.0);
        MenuItem menuItem2 =new MenuItem("Funghi",2,  23.0);
        menuRepo.addMenuItem(menuItem1);
        menuRepo.addMenuItem(menuItem2);
        Assertions.assertEquals(mainService.getMenuData().size(),2);
    }
}
