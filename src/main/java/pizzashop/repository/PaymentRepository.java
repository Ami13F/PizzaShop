package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentRepository {
    private String filename = "data/payments.txt";
    private List<Payment> paymentList;

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
        this.filename = "data/payments.txt";
        readPayments();
    }

    public PaymentRepository(final String filename){
        this.paymentList = new ArrayList<>();
        this.filename = filename;
    }

    private void readPayments(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                paymentList.add(payment);
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, e.getMessage());
        }
    }

    private Payment getPayment(String line){
        Payment item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type.toUpperCase()), amount);
        return item;
    }

    public void addPay(Payment payment){
        paymentList.add(payment);
    }
    public void add(Payment payment){
        this.addPay(payment);
        writeAll();
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());


        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
            for (Payment p:paymentList) {
                String product = p.toString();
                Logger.getLogger(this.getClass().getName()).log(Level.ALL, product);
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.ALL, e.getMessage());
        }
    }

}
