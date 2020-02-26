package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pizzashop.Main;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KitchenGUIController {
    @FXML
    private ListView kitchenOrdersList;
    @FXML
    public Button cook;
    @FXML
    public Button ready;

    protected static final ObservableList<String> order = FXCollections.observableArrayList();
    private Object selectedOrder;
    private Calendar now = Calendar.getInstance();
    private String extractedTableNumberString = "";
    private int extractedTableNumberInteger;
    //thread for adding data to kitchenOrderList
    public final Thread addOrders = new Thread(()->
            Platform.runLater(()->
                kitchenOrdersList.setItems(order)
            ));

    public void initialize() {
        //starting thread for adding data to kitchenOrderList
        addOrders.setDaemon(true);
        addOrders.start();
        //Controller for Cook Button
        cook.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if(selectedOrder == null){
                Alert alert = new Alert(Alert.AlertType.WARNING, "No Order selected for cooking");
                alert.showAndWait();
                return;
            }
            kitchenOrdersList.getItems().remove(selectedOrder);
            kitchenOrdersList.getItems().add(selectedOrder.toString()
                     .concat(" Cooking started at: ").toUpperCase()
                     .concat(now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)));
        });
        //Controller for Ready Button
        ready.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if(selectedOrder == null){
                Alert alert = new Alert(Alert.AlertType.WARNING, "No Order selected.");
                alert.showAndWait();
                return;
            }
            kitchenOrdersList.getItems().remove(selectedOrder);
            extractedTableNumberString = selectedOrder.toString().subSequence(5, 6).toString();
            extractedTableNumberInteger = Integer.valueOf(extractedTableNumberString);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "--------------------------");
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Table " + extractedTableNumberInteger +" ready at: " + now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE));
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "--------------------------");
        });
    }
}