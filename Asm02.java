package vn.funix.fx17332.java.asm02;

import vn.funix.fx17332.java.asm02.models.Account;
import vn.funix.fx17332.java.asm02.models.Bank;
import vn.funix.fx17332.java.asm02.models.Customer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asm02 {
    private static final Bank bank = new Bank();
    private static Scanner sc = new Scanner(System.in);
    private static Customer newCustomer = new Customer();
    private static List<Account> accounts = newCustomer.getAccounts();

    public static void main(String[] args) {
        displayMainMenu();
        boolean quit;
        int choice;
        do {
            quit = false;
            do {
                try {
                    choice = sc.nextInt();
                    break;
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("Chuc nang khong dung dinh dang. Vui long nhap lai: ");
                }
            }while (true);
                switch (choice) {
                    case 1:
                        themKhachHang();
                        break;
                    case 2:
                        themTaiKhoanKhachHang();
                        break;
                    case 3:
                        hienThiDanhSachKhachHang();
                        break;
                    case 4:
                        timTheoCCCD();
                        break;
                    case 5:
                        timTheoTenKH();
                        break;
                    case 0:
                        quit = true;
                        System.out.println("Ban da thoat khoi chuong trinh");
                        break;
                    default:
                        System.out.println("Khong co chuc nang nay. Vui long chon dung chuc nang");
                        break;
                }
        } while (choice !=0);


    }


    public static void displayMainMenu() {
        System.out.println("+----------+-----------------------------------+---------+");
        System.out.println("| NGAN HANG SO  |  FX17332@v2.0.0                        |");
        System.out.println("+----------+-----------------------------------+---------+");
        System.out.println("| 1. Them khach hang                                     |");
        System.out.println("| 2. Them tai khoan cho khach hang                       |");
        System.out.println("| 3. Hien thi danh sach khach hang                       |");
        System.out.println("| 4. Tim theo CCCD                                       |");
        System.out.println("| 1. Tim theo ten khach hang                             |");
        System.out.println("| 0. Thoat:                                              |");
        System.out.println("+----------+-----------------------------------+---------+");
        System.out.print("Chuc nang: ");

    }

    public static void themKhachHang() {
        sc.nextLine();
        Customer newCustomer = new Customer();
        System.out.println("Nhap ten khach hang: ");
        String tenKH = "";
        String cccd = "";
        do {
            try {
                tenKH = sc.nextLine();
                if (!validName(tenKH)) {
                    System.out.println("Ten khach hang khong hop le. Vui long nhap lại ten");
                } else {
                    newCustomer.setName(tenKH);
                    System.out.println("Nhap so CCCD: ");
                }
            } catch (Exception e) {
                System.out.println("Ten khach hang khong hop le");
            }
        } while ((!validName(tenKH)));

        do {
            try {
                cccd = sc.nextLine();
                if (!validNumber(cccd)) {
                    System.out.println("Nhap lai so CCCD: ");
                } else {
                    newCustomer.setCustomerId(cccd);
                    break;
                }
            } catch (Exception e) {
                System.out.println("So CCCD khong hop le");
            }

        } while (true);
        bank.addCustomer(newCustomer);
        System.out.println("Da them khach hang " + tenKH + " vao he thong");

    }

    public static boolean validName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            System.out.println("Ten khach hang khong hop le");
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean validNumber(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                System.out.println("So CCCD khong hop le");
                return false;
            }
        }
        if (id.length() != 12) {
            System.out.println("Độ dài số CCCD không hợp lệ.");
            return false;
        }
        return true;
    }

    public static void themTaiKhoanKhachHang() {
        Account newAccount = new Account();
        System.out.println("Nhap CCCD khach hang: ");
        String cccd = "";
        String sotk = "";
        do {
            try {
                cccd = sc.next();
                if (!bank.isCustomerExisted(cccd)) {
                    System.out.println("Tai khoan khong ton tai. Vui long nhap lai so CCCD: ");
                }
            } catch (Exception e) {
                System.out.println("So tai khoan khong hop le");
            }

        } while (!bank.isCustomerExisted(cccd));

        System.out.println("Nhap ma STK gom 6 chu so: ");
        do {
            try {
                sotk = sc.next();
                for (int i = 0; i < sotk.length(); i++) {
                    if (sotk.charAt(i) < '0' || sotk.charAt(i) > '9') {
                        System.out.println("So tai khoan khong dung dinh dang");
                        break;
                    }
                }
                if (sotk.length() != 6) {
                    System.out.println("Độ dài số tai khoan không hợp lệ.");
                }
                newAccount.setAccountNumber(sotk);
            } catch (Exception e) {
                System.out.println();
            }
        } while (sotk.length() != 6);
        System.out.println("Nhap so du: ");
        double soDu;
        do {
            soDu = sc.nextDouble();
            try {
                if (soDu < 50000) {
                    System.out.println("So du khong duoc ít hon 50,000 VND. Vui long nhap lại: ");
                } else {
                    newAccount.setBalance(soDu);
                    break;
                }

            } catch (Exception e) {
                System.out.println("So du khong hop le");
            }
        } while (soDu < 50000);
        List<Customer> customerList = bank.getCustomers();
        DecimalFormat formatter = new DecimalFormat("#,###");
        for (Customer i : bank.getCustomers()) {
            if (i.getCustomerId().equals(cccd)) {
                i.addAccount(newAccount);
                System.out.println("So tai khoan " + sotk + " co " + formatter.format(soDu) + " trong tai khoan");
                break;
            }
        }
    }


    public static void hienThiDanhSachKhachHang() {
        List<Customer> customerList = bank.getCustomers();
        System.out.println("Danh sach KH: ");
        for (int i = 0; i < customerList.size(); i++) {
            customerList.get(i).displayInformation();
        }
    }

    public static void timTheoCCCD() {
        System.out.println("Nhap so CCCD can tim: ");
        String cccd = sc.next();
        for (Customer i : bank.getCustomers()) {
            if (i.getCustomerId().equals(cccd)) {
                i.displayInformation();
                break;
            } else {
                System.out.println("Khong co so CCCD nay trong he thong");
                break;
            }
        }
    }

    private static void timTheoTenKH() {
        System.out.println("Nhap ten khach hang: ");
        sc.nextLine();
        String name = sc.nextLine();
        boolean check = false;
        for (Customer i : bank.getCustomers()) {
            if (i.getName().contains(name)) {
                System.out.println("Tim thay " + name + " trong he thong");
                i.displayInformation();
                check = true;
            }
        }
        if (check == false) {
            System.out.println("Khong tim thay khach hang");
        }
    }


}
