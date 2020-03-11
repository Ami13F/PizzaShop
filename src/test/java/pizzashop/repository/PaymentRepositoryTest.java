package pizzashop.repository;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentRepositoryTest {

    private static PaymentRepository paymentRepository;

    @BeforeAll
    static void init(){
        PaymentRepositoryTest.paymentRepository =
                new PaymentRepository("/data/pay_test.csv");
    }


    @Tag("integration")
    @Test
    void addPay_TC2_BVA(){
        Payment payment = new Payment(7, PaymentType.CASH,43.20);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        assertEquals(1, (afterSize - beforeSize));
    }

    @Tag("integration")
    @Test
    void addPay_TC3_BVA(){
        Payment payment = new Payment(1, PaymentType.CASH,75.3);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        assertEquals(1, (afterSize - beforeSize));
    }

    @Tag("integration")
    @Test
    void addPay_TC4_BVA(){
        Payment payment = new Payment(2, PaymentType.CARD,80.0);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        assertEquals(1, (afterSize - beforeSize));
    }

    @DisplayName("the amount can not be 0")
    @Test
    void addPay_TC5_BVA(){
        Payment payment = new Payment(5, PaymentType.CARD,0);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        assertEquals(0, (afterSize - beforeSize));
    }

    @Tag("integration")
    @Test
    void addPay_TC6_BVA(){
        Payment payment = new Payment(6, PaymentType.CASH,1);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        assertEquals(1, (afterSize - beforeSize));
    }
    @Order(2)
    @Tag("integration")
    @Test
    void addPay_TC7_BVA(){
        Payment payment = new Payment(7, PaymentType.CARD,Double.MAX_VALUE-1);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        assertEquals(1, (afterSize - beforeSize));
    }

    @Order(1)
    @Disabled
    @Tag("integration")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void addPay_TC8_BVA(){
        Payment payment = new Payment(8, PaymentType.CARD,Double.MAX_VALUE);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        assertEquals(1, (afterSize - beforeSize));
    }
}