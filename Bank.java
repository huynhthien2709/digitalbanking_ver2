package vn.funix.fx17332.java.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
        private final String id;
        List<Customer> customers = new ArrayList<Customer>();

    public Bank() {
        this.customers =new ArrayList<Customer>();
        this.id = String.valueOf(UUID.randomUUID());

    }


    public String getId() {
        return id;
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }



    public void addCustomer(Customer newCustomer){
        for (int i=0; i<customers.size(); i++){
            if(customers.get(i).getCustomerId().equals(newCustomer.getCustomerId())){
                System.out.println("Khach hang này đã tồn tại");
                return;
            }
        }
        customers.add(newCustomer);
//        System.out.println(customers.size());
    }
    public boolean isCustomerExisted(String customerId){
        for (int i=0; i< customers.size(); i++){
            if(customers.get(i).getCustomerId().equals(customerId)){
                return true;
            }
        }
        return  false;
    }

}
