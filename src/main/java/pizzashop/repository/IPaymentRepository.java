package pizzashop.repository;

import pizzashop.model.Payment;

import java.util.List;

public interface IPaymentRepository {
    void addPay(Payment payment);
    void add(Payment payment);
    List<Payment> getAll();
    void clear();
    void writeAll();
}
