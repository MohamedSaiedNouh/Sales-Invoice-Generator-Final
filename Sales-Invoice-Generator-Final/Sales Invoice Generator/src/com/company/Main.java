package com.company;

import com.company.Controller.CustomExceptions;
import com.company.Model.FileOperations;
import com.company.Model.InvoiceHeader;
import com.company.Model.InvoiceLine;
import com.company.View.InvoiceFrame;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args)  {

        importPreDefinedInvoices();
        InvoiceFrame invoiceFrame = new InvoiceFrame();
        invoiceFrame.setVisible(true);

    }

    private static void importPreDefinedInvoices()  {
        FileOperations fileOperations = new FileOperations();
        ArrayList<InvoiceHeader> readInvoiceHeader = new ArrayList<>();
        ArrayList<InvoiceLine> readInvoiceLines = new ArrayList<>();
        int i,j;


        try {
            JOptionPane.showMessageDialog(null,"Please select Invoice Header File","Please select Invoice Header File Information Message",JOptionPane.INFORMATION_MESSAGE);
            readInvoiceHeader = fileOperations.readInvoiceHeaderFile();
        }

        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,"File not found","File not found Error Message",JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JOptionPane.showMessageDialog(null,"Please select Invoice Line File","Please select Invoice Line File Information Message",JOptionPane.INFORMATION_MESSAGE);

            readInvoiceLines = fileOperations.readInvoiceLinesFile();
        }

        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,"File not found","File not found Error Message",JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        for(i=0; i<readInvoiceHeader.size(); i++)
        {

            System.out.println("Invoice Number : " + readInvoiceHeader.get(i).getInvoiceNumber());
            System.out.println("{");
            System.out.println("Invoice Date : " + readInvoiceHeader.get(i).getInvoiceDate() + ", " + "Customer Name : " + readInvoiceHeader.get(i).getCustomerName() + ", " + "Invoice Total : " + readInvoiceHeader.get(i).getInvoiceTotal());

            for (j=0; j<readInvoiceLines.size();j++)
            {
                if (readInvoiceHeader.get(i).getInvoiceNumber() == (readInvoiceLines.get(j).getInvoiceNumber())) {

                    System.out.println("Item Name : " + readInvoiceLines.get(j).getItemName() + ", "
                            + "Item Price : " + readInvoiceLines.get(j).getItemPrice() + ", "
                            + "Item Count : " + readInvoiceLines.get(j).getItemCount() + ", "
                            + "Item total : " + readInvoiceLines.get(j).getInvoiceLineTotal());

                }
            }
            System.out.println("}");
        }

    }



}
