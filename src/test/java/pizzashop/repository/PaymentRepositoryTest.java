package pizzashop.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository ;

    @BeforeAll
    public static void init(){
        paymentRepository = new PaymentRepository();
    }

    @Test
    void addPay() {

    }
    @Test
    void addPay_TC2_BVA(){
        Payment payment = new  Payment(7,PaymentType.CASH,43.20);

    }
}