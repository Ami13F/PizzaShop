package pizzashop.service;

import pizzashop.model.MenuItem;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class MainService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public MainService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuItem> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> paymentList=getPayments();
        if (paymentList == null)
            return total;
        if (paymentList.isEmpty())
            return total;
        for (Payment p:paymentList){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}
