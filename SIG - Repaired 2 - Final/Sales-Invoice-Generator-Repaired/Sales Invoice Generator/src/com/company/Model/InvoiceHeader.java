package com.company.Model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private int invoiceNumber;
    private String invoiceDate;
    private String customerName;
    private double invoiceTotal;

    private ArrayList<InvoiceLine> invoiceLines;



    public InvoiceHeader(int invoiceNumber, String invoiceDate , String customerName , double invoiceTotal) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        this.invoiceTotal = invoiceTotal;
    }

    public InvoiceHeader(int invoiceNumber, String invoiceDate , String customerName, ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        this.invoiceLines = new ArrayList<>();
    }

    public InvoiceHeader(int invoiceNumber, String invoiceDate, String customerName, double invoiceTotal, ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.invoiceTotal = invoiceTotal;
        this.invoiceLines = invoiceLines;
    }

    public InvoiceHeader() {

    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {

        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public void addInvoiceLine(InvoiceLine invoiceLine)
    {
        getInvoiceLines().add(invoiceLine);
    }



    public double calculateInvoiceTotal ( ArrayList<InvoiceLine> invoiceLines)
    {
        double total=0.0;
        for(int i=0; i<invoiceLines.size();i++)
        {
           total+= invoiceLines.get(i).getInvoiceLineTotal();
        }
        return total;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }
}
