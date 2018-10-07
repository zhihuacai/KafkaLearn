package com.kafka.learning.Final.test;

import java.io.IOException;

/**
 * Created by hadoop on 10/6/18.
 */
public class test1 {
    public static int quantity;
    public static double itemPrice;
    public static double salesAccounts;

    public static int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static double getSalesAccounts() {
        return salesAccounts;
    }

    public void setSalesAccounts(double salesAccounts) {
        this.salesAccounts = salesAccounts;
    }

    public static double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }


    public static void main(String[] args) throws IOException, InterruptedException
    {


        salesAccounts = getQuantity() * getItemPrice();
        System.out.println("salesAccounts: " + salesAccounts);

    }

}
