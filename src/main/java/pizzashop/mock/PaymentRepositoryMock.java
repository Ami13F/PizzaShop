package pizzashop.mock;

import pizzashop.model.Payment;
import pizzashop.repository.IPaymentRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class PaymentRepositoryMock implements IPaymentRepository {

    @Override
    public void addPay(Payment payment) {
        throw new NotImplementedException();
    }

    @Override
    public void add(Payment payment) {
        throw new NotImplementedException();
    }

    @Override
    public List<Payment> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public void writeAll() {
        throw new NotImplementedException();
    }
}
