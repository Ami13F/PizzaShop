package pizzashop.validator;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

public class PaymentValidator {

    public static void validate(Payment paymnet) throws IllegalArgumentException{
        if (paymnet.getAmount() <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (!paymnet.getType().equals(PaymentType.CARD) && !paymnet.getType().equals(PaymentType.CASH) ){
            throw new IllegalArgumentException("Payment type is invalid");
        }
        if (paymnet.getTableNumber() < 1 || paymnet.getTableNumber() > 8){
            throw new IllegalArgumentException("Table must be in interval.");
        }
    }
}
