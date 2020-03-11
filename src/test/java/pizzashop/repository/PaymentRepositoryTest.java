package pizzashop.repository;

import org.junit.jupiter.api.Assertions;
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
    void addPay_TC1_ECP() {
        final Payment payment = new Payment(1, PaymentType.CARD,13.97);
        Assertions.assertTrue(paymentRepository.getAll().isEmpty());
        paymentRepository.addPay(payment);
        Assertions.assertFalse(paymentRepository.getAll().isEmpty());
    }

    @Test
    void addPay_TC3_ECP() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final Payment payment = new Payment(7,
                    PaymentType.valueOf("BONURI"),12.0);
            paymentRepository.addPay(payment);
        });
    }

    @Test
    void addPay_TC5_ECP() {
        final Payment payment = new Payment(2, PaymentType.CASH, -1.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.addPay(payment);
        });
    }

    @Test
    void addPay_TC6_ECP() {
        final Payment payment = new Payment(0, PaymentType.CASH, 31.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.addPay(payment);
        });
    }

    @Test
    void addPay_TC7_ECP() {
        final Payment payment = new Payment(9, PaymentType.CASH, 12.3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.addPay(payment);
        });
    }

    @Test
    void addPay_TC1_BVA() {
        final Payment payment = new Payment(8,	PaymentType.CARD, 43.15);
        final int countNum = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        Assertions.assertEquals(paymentRepository.getAll().size() - 1, countNum);
    }

    @Test
    void addPay_TC2_BVA(){
        final Payment payment = new Payment(7, PaymentType.CASH,43.20);
    }
}