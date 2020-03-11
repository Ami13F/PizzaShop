package pizzashop.repository;


import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.concurrent.TimeUnit;


class PaymentRepositoryTest {

    private static PaymentRepository paymentRepository;

    @BeforeAll
    static void init(){
        PaymentRepositoryTest.paymentRepository =
                new PaymentRepository("/data/pay_test.csv");
    }

    @Order(1)
    @Tag("integration")
    @Test
    void addPay_TC1_ECP() {
        final Payment payment = new Payment(1, PaymentType.CARD,13.97);
        System.out.println(paymentRepository.getAll().size());
        paymentRepository.getAll().forEach(System.out::println);
        int count = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        Assertions.assertEquals(paymentRepository.getAll().size(), count + 1);
    }

    @Order(5)
    @Tag("integration")
    @Test
    void addPay_TC3_ECP() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final Payment payment = new Payment(7,
                    PaymentType.valueOf("BONURI"),12.0);
            paymentRepository.addPay(payment);
        });
    }

    @Order(6)
    @Tag("integration")
    @Test
    void addPay_TC5_ECP() {
        final Payment payment = new Payment(2, PaymentType.CASH, -1.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.addPay(payment);
        });
    }

    @Order(7)
    @Tag("integration")
    @Test
    void addPay_TC6_ECP() {
        final Payment payment = new Payment(0, PaymentType.CASH, 31.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.addPay(payment);
        });
    }

    @Order(8)
    @Tag("integration")
    @Test
    void addPay_TC7_ECP() {
        final Payment payment = new Payment(9, PaymentType.CASH, 12.3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.addPay(payment);
        });
    }

    @Order(4)
    @Tag("integration")
    @Test
    void addPay_TC1_BVA() {
        final Payment payment = new Payment(8, PaymentType.CARD, 43.15);
        final int countNum = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        Assertions.assertEquals(paymentRepository.getAll().size() - 1, countNum);
    }

    @Order(9)
    @Tag("integration")
    @Test
    void addPay_TC2_BVA(){
        Payment payment = new Payment(7, PaymentType.CASH,43.20);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        Assertions.assertEquals(1, (afterSize - beforeSize));
    }

    @Order(10)
    @Tag("integration")
    @Test
    void addPay_TC3_BVA(){
        Payment payment = new Payment(1, PaymentType.CASH,75.3);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        Assertions.assertEquals(1, (afterSize - beforeSize));
    }

    @Order(11)
    @Tag("integration")
    @Test
    void addPay_TC4_BVA(){
        Payment payment = new Payment(2, PaymentType.CARD,80.0);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        Assertions.assertEquals(1, (afterSize - beforeSize));
    }

    @Order(12)
    @DisplayName("the amount can not be 0")
    @Test
    void addPay_TC5_BVA(){
        Payment payment = new Payment(5, PaymentType.CARD,0);
        int beforeSize = paymentRepository.getAll().size();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.addPay(payment);
        });
    }

    @Order(13)
    @Tag("integration")
    @Test
    void addPay_TC6_BVA(){
        Payment payment = new Payment(6, PaymentType.CASH,1);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        Assertions.assertEquals(1, (afterSize - beforeSize));
    }

    @Order(2)
    @Tag("integration")
    @Test
    void addPay_TC7_BVA(){
        Payment payment = new Payment(7, PaymentType.CARD,Double.MAX_VALUE-1);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        Assertions.assertEquals(1, (afterSize - beforeSize));
    }

    @Order(3)
    @Tag("integration")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void addPay_TC8_BVA(){
        Payment payment = new Payment(8, PaymentType.CARD,Double.MAX_VALUE);
        int beforeSize = paymentRepository.getAll().size();
        paymentRepository.addPay(payment);
        int afterSize = paymentRepository.getAll().size();
        Assertions.assertEquals(1, (afterSize - beforeSize));
    }
}