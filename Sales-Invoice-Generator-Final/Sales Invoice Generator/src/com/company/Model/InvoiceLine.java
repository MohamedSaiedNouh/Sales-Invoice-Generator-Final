package com.company.Model;

public class InvoiceLine {
    private int   invoiceNumber;
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private InvoiceHeader invoiceHeader;

    public InvoiceLine(int invoiceNumber,String itemName, double itemPrice, int itemCount ) {
        this.invoiceNumber = invoiceNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;

    }

    public InvoiceLine(String itemName, double itemPrice, int itemCount, InvoiceHeader invoiceHeader) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.invoiceHeader = invoiceHeader;
    }

    public InvoiceLine() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public double getInvoiceLineTotal(){
        return (itemCount*itemPrice) ;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }



    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
