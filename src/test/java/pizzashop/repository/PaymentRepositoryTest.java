package pizzashop.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

class PaymentRepositoryTest {
    private static PaymentRepository paymentRepository;

    @BeforeAll
    static void init(){
        PaymentRepositoryTest.paymentRepository =
                new PaymentRepository("/data/pay_test.csv");
    }

    @Test
    void addPay() {

    }

    @Test
    void addPay_TC2_BVA(){
        Payment payment = new Payment(7, PaymentType.CASH,43.20);
    }
}