package pizzashop.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.PaymentType;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentAlert implements PaymentOperation {
    private MainService service;

    public PaymentAlert(MainService service){
        this.service=service;
    }

    @Override
    public void cardPayment() {
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Paying by card...");
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Please insert your card!");
    }
    @Override
    public void cashPayment() {
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Paying cash...");
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Please show the cash...!");
    }
    @Override
    public void cancelPayment() {
        Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Payment choice needed...");
    }
      public void showPaymentAlert(int tableNumber, double totalAmount ) {
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment for Table "+tableNumber);
        paymentAlert.setHeaderText("Total amount: " + totalAmount);
        paymentAlert.setContentText("Please choose payment option");
        ButtonType cardPayment = new ButtonType("Pay by Card");
        ButtonType cashPayment = new ButtonType("Pay Cash");
        ButtonType cancel = new ButtonType("Cancel");
        paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
        Optional<ButtonType> result = paymentAlert.showAndWait();
        if (result.isPresent() && result.get() == cardPayment) {
            cardPayment();
            service.addPayment(tableNumber, PaymentType.CARD,totalAmount);
        } else if (result.isPresent() && result.get() == cashPayment) {
            cashPayment();
            service.addPayment(tableNumber, PaymentType.CASH,totalAmount);
        } else if (result.isPresent() && result.get() == cancel) {
             cancelPayment();
        } else {
            cancelPayment();
        }
    }
}
