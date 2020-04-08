package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.IMenuRepository;
import pizzashop.repository.IPaymentRepository;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

class MainServiceTest {

    private static IMenuRepository menuRepo = null;
    private static IPaymentRepository paymentRepository;

    private static MainService mainService;
    @BeforeAll
    static void init(){
        MainServiceTest.paymentRepository =
                new PaymentRepository("/data/pay_test.csv");
        MainServiceTest.mainService = new MainService(menuRepo, paymentRepository);
    }
    @BeforeEach
    public void clear_list(){
        mainService.clear();
    }

    @DisplayName("one payment added invalid")
    @Test
    void getPay_TC1() {
        mainService.addPayment(1,PaymentType.CARD,10f);
        Assertions.assertEquals(mainService.getTotalAmount(PaymentType.CASH), 0.0f);
    }

    @DisplayName("one payment added valid")
    @Test
    void getPay_TC2() {
        mainService.addPayment(1,PaymentType.CARD,10f);
        Assertions.assertEquals(mainService.getTotalAmount(PaymentType.CARD), 10f);
    }

    @DisplayName("no payment added")
    @Test
    void getPay_TC3() {
        Assertions.assertEquals(mainService.getTotalAmount(PaymentType.CASH), 0.0f);
    }

    @DisplayName("add 2 pay invalid")
    @Test
    void getPay_TC4() {
        mainService.addPayment(1,PaymentType.CARD,10f);
        mainService.addPayment(1,PaymentType.CARD,10f);
        Assertions.assertEquals(mainService.getTotalAmount(PaymentType.CASH), 0.0f);
    }
    @DisplayName("add 2 pay valid")
    @Test
    void getPay_TC5() {
        mainService.addPayment(1,PaymentType.CARD,15f);
        mainService.addPayment(1,PaymentType.CARD,15f);
        Assertions.assertEquals(mainService.getTotalAmount(PaymentType.CARD), 30.0f);
    }


}