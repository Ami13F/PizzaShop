package pizzashop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.MenuItem;
import pizzashop.repository.IMenuRepository;
import pizzashop.repository.IPaymentRepository;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class MainServiceITest1 {
    private static IMenuRepository menuRepo;
    private static IPaymentRepository paymentRepository;

    private static MainService mainService;

    private MenuItem item = Mockito.mock(MenuItem.class);
    private MenuItem item2 = Mockito.mock(MenuItem.class);
    @BeforeAll
    static void init(){
        MainServiceITest1.menuRepo = new MenuRepository("/data/menu_test.csv");
        MainServiceITest1.paymentRepository = new PaymentRepository("/data/pay_test.csv");
        MainServiceITest1.mainService = new MainService(menuRepo, paymentRepository);
    }
    @BeforeEach
    public void clear_list(){
        mainService.clear();
    }

    @Test
    void getMenuData_TC1() {
        Assertions.assertTrue(mainService.getMenuData().isEmpty());
    }

    @Test
    void getMenuData_TC2() {
        menuRepo.addMenuItem(item);
        Assertions.assertFalse(mainService.getMenuData().isEmpty());
        menuRepo.addMenuItem(item);
        Assertions.assertEquals(mainService.getMenuData().size(), 2);
    }
}