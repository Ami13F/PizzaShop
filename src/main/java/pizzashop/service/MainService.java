package pizzashop.service;

import pizzashop.model.MenuItem;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.IMenuRepository;
import pizzashop.repository.IPaymentRepository;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class MainService {

    private IMenuRepository menuRepo;
    private IPaymentRepository payRepo;

    public MainService(IMenuRepository menuRepo, IPaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuItem> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public void clear(){
        payRepo.clear();
    }

    /**
     * Compute total amount for payment type.
     * @param type CASH or CARD
     * @return total
     */
    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> paymentList=getPayments();
        if (paymentList.size() == 1){
            Payment p = paymentList.get(0);
            if(p.getType().equals(type))
                return p.getAmount();
            else return total;
        }

        for (Payment p:paymentList){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}
