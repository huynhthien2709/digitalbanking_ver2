package vn.funix.fx17332.java.asm02.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    List<Account> accounts = new ArrayList<Account>();


    public Customer(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Customer() {

    }

    public List<Account> getAccounts() {
        return accounts;
    }
    public boolean isPremium(){
        for (int i=0; i<accounts.size(); i++){
            if(accounts.get(i).isPremium() == true){
                return true;            }
        }
        return false;
    }


    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account newAccount){
        for (int i=0; i<accounts.size(); i++){
            if ((accounts.get(i).getAccountNumber().equals(newAccount))){
                 System.out.println("Tài khoản đã tồn tại trên hệ thống");
               return;
            }
        }
        accounts.add(newAccount);
//        System.out.println(accounts.size());
    }
    public int getBalance(){
        int totalBalance = 0;
        for (int i=0; i<accounts.size(); i++){
            totalBalance +=  accounts.get(i).getBalance();
        }
        return totalBalance;
    }
    public void displayInformation(){
        String strPremium;
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(isPremium() == true){
            strPremium = "Premium";
        }else {
            strPremium = "Normal";
        }
        System.out.println(String.format("%s  |          %s | %s | %s",  getCustomerId(), getName(), strPremium, formatter.format(getBalance()))+"đ");
        for (int i=0; i< accounts.size(); i++){
            System.out.println( i+1  +"       "+  accounts.get(i).getAccountNumber() + "                         "+ formatter.format(accounts.get(i).getBalance())+"đ");
        }
//        System.out.println(accounts.size());
    }
}
