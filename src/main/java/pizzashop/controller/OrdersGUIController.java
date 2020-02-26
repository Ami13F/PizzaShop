package pizzashop.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pizzashop.exception.PizzaShopException;
import pizzashop.model.MenuItem;
import pizzashop.service.PaymentAlert;
import pizzashop.service.MainService;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OrdersGUIController {

    @FXML
    private ComboBox<Integer> orderQuantity;
    @FXML
    private TableView orderTable;
    @FXML
    private TableColumn tableQuantity;
    @FXML
    protected TableColumn tableMenuItem;
    @FXML
    private TableColumn tablePrice;
    @FXML
    private Label pizzaTypeLabel;
    @FXML
    private Button addToOrder;
    @FXML
    private Label orderStatus;
    @FXML
    private Button placeOrder;
    @FXML
    private Button orderServed;
    @FXML
    private Button payOrder;
    @FXML
    private Button newOrder;

    private List<Double> orderPaymentList = FXCollections.observableArrayList();
    public static double getTotalAmount() {
        return totalAmount;
    }
    public static void setTotalAmount(double totalAmount) {
        OrdersGUIController.totalAmount = totalAmount;
    }

    private MainService service;
    private int tableNumber;

    private TableView<MenuItem> table = new TableView<>();
    private ObservableList<MenuItem> menuData;
    private Calendar now = Calendar.getInstance();
    private static double totalAmount;

    public void setService(MainService service, int tableNumber){
        this.service=service;
        this.tableNumber=tableNumber;
        initData();
        payOrder.setDisable(true);
    }

    private void placeOrder() throws PizzaShopException{
        List<String> orderList = menuData.stream()
                .filter(x -> x.getQuantity() > 0)
                .map(menuItem -> menuItem.getQuantity() + " " + menuItem.getItemName())
                .collect(Collectors.toList());
        ObservableList<String> observableList = FXCollections.observableList(orderList);
        if(observableList.isEmpty()) throw new PizzaShopException("empty order list");
        KitchenGUIController.order.add("Table" + tableNumber +" "+ orderList.toString());
        orderStatus.setText("Order placed at: " +  now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE));
        payOrder.setDisable(false);
    }

    private void initData(){
        menuData = FXCollections.observableArrayList(service.getMenuData());
        menuData.setAll(service.getMenuData());
        orderTable.setItems(menuData);

        //Controller for Place Order Button
        placeOrder.setOnAction(event ->{
            try {
                placeOrder();
            } catch (PizzaShopException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Empty order");
                alert.showAndWait();
                return;
            }
        });

        //Controller for Order Served Button
        orderServed.setOnAction(event -> orderStatus.setText("Served at: " + now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)));

        //Controller for Pay Order Button
        payOrder.setOnAction(event -> {
            orderPaymentList= menuData.stream()
                    .filter(x -> x.getQuantity()>0)
                    .map(menuItem -> menuItem.getQuantity()* menuItem.getPrice())
                    .collect(Collectors.toList());
            setTotalAmount(orderPaymentList.stream().mapToDouble(Double::doubleValue).sum());
            orderStatus.setText("Total amount: " + getTotalAmount());
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "--------------------------");
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Table: " + tableNumber);
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "Total: " + getTotalAmount());
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, "--------------------------");
            PaymentAlert pay = new PaymentAlert(service);
            pay.showPaymentAlert(tableNumber, getTotalAmount());
        });
    }

    public void initialize(){

        //populate table view with menuData from OrderGUI
        table.setEditable(true);
        tableMenuItem.setCellValueFactory(
                new PropertyValueFactory<MenuItem, String>("menuItem"));
        tablePrice.setCellValueFactory(
                new PropertyValueFactory<MenuItem, Double>("price"));
        tableQuantity.setCellValueFactory(
                new PropertyValueFactory<MenuItem, Integer>("quantity"));

        //bind pizzaTypeLabel and quantity combo box with the selection on the table view
        orderTable.getSelectionModel().selectedItemProperty().addListener((ChangeListener<MenuItem>) (observable, oldValue, newValue) -> pizzaTypeLabel.textProperty().bind(newValue.itemNameProperty()));

        //Populate Combo box for Quantity
        ObservableList<Integer> quantityValues =  FXCollections.observableArrayList(1, 2,3,4,5);
        orderQuantity.getItems().addAll(quantityValues);
        orderQuantity.setPromptText("Quantity");

        //Controller for Add to order Button
        addToOrder.setOnAction(event -> orderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MenuItem>(){
        @Override
        public void changed(ObservableValue<? extends MenuItem> observable, MenuItem oldValue, MenuItem newValue){
        oldValue.setQuantity(orderQuantity.getValue());
        orderTable.getSelectionModel().selectedItemProperty().removeListener(this);
            }
        }));

        //Controller for Exit table Button
        newOrder.setOnAction(event -> {
            Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Exit table?",ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = exitAlert.showAndWait();
            if (result.get() == ButtonType.YES){
                Stage stage = (Stage) newOrder.getScene().getWindow();
                stage.close();
                }
        });
    }
}