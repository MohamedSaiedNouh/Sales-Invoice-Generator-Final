package com.company.View.RightSideView;

import javax.swing.*;
import java.awt.*;

public class TextFieldsPanel extends JPanel {
    private  JLabel invoiceNumber;
    private JTextField invoiceDate;
    private JTextField customerName;
    private  JLabel invoiceTotal;
    private static TextFieldsPanel TextFieldsPanelSingleInstance = null;




    private TextFieldsPanel()
    {

        invoiceNumber  = new JLabel("");
        JLabel label1 = new JLabel("Invoice Number");
        label1.setLabelFor(invoiceNumber);
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        invoiceNumber.setHorizontalAlignment(SwingConstants.LEFT);

        invoiceDate    = new JTextField(10);
        JLabel label2 = new JLabel("Invoice Date");
        label2.setLabelFor(invoiceDate);

        customerName   = new JTextField(10);
        JLabel label3 = new JLabel("Customer Name");
        label3.setLabelFor(customerName);

        invoiceTotal   = new JLabel("");
        JLabel label4 = new JLabel("Invoice Total");
        label4.setLabelFor(invoiceTotal);

        setLayout(new GridLayout(4,1));
        add(label1);
        add(invoiceNumber);
        add(label2);
        add(invoiceDate);
        add(label3);
        add(customerName);
        add(label4);
        add(invoiceTotal);

    }

    public static TextFieldsPanel Singleton()
    {
        if (TextFieldsPanelSingleInstance == null)
        {
            TextFieldsPanelSingleInstance = new TextFieldsPanel();
        }
        return TextFieldsPanelSingleInstance;
    }


    public void setInvoiceNumberLabel(String newInvoiceNumber)
    {
        invoiceNumber.setText(newInvoiceNumber);
    }

    public void setInvoiceTotalLabel(String newInvoiceTotal) {
        invoiceTotal.setText(newInvoiceTotal);
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate.setText(invoiceDate);
    }

    public void setCustomerName(String customerName) {
        this.customerName.setText(customerName);
    }

    public int getInvoiceNumber() {
        return Integer.parseInt(invoiceNumber.getText());
    }

    public String getInvoiceDate() {
        return invoiceDate.getText();
    }

    public String getCustomerName() {
        return customerName.getText();
    }

    public double getInvoiceTotal() {
        return Double.parseDouble(invoiceTotal.getText());
    }

}
