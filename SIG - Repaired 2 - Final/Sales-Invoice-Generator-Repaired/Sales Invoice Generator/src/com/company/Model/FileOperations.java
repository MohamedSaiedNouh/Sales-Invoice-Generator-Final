package com.company.Model;

import com.company.Controller.CustomExceptions;
import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FileOperations extends Component {
    private ArrayList<InvoiceHeader> invoiceHeaders;
    private ArrayList<InvoiceLine> invoiceLines;
    String line;
    String[] arr;

    public ArrayList<InvoiceHeader> readInvoiceHeaderFile() throws IOException {

        invoiceHeaders = new ArrayList<>();
        JFileChooser invoiceHeaderChooser = new JFileChooser();
        int result = invoiceHeaderChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = invoiceHeaderChooser.getSelectedFile().getPath();

            try {

                /* Check the file format or extension*/
                // if file is csv extension
                if (filePath.substring(filePath.length() - 3).equalsIgnoreCase("csv")) {

                    File invoiceHeaderFile = new File(filePath);
                    BufferedReader invoiceHeaderFileReader = new BufferedReader(new FileReader(invoiceHeaderFile));
                    while ((line = invoiceHeaderFileReader.readLine()) != null) {
                        arr = line.split(",");

                        // if the Date String is not null
                        if (arr[1] != null || arr[1].length() != 0) {
                            try {
                                String strDate = arr[1];
                                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = (Date) formatter.parse(strDate);
                                formatter.setLenient(false);
                                invoiceHeaders.add(new InvoiceHeader(Integer.parseInt(arr[0]), arr[1], arr[2], Double.parseDouble(arr[3])));
                            } catch (ParseException e) {
                                JOptionPane.showMessageDialog(null, "Wrong Date Format", "Wrong Date Format Error Message", JOptionPane.ERROR_MESSAGE);
                            }


                        }
                    }


                }
                else {
                    CustomExceptions customExceptions = new CustomExceptions();
                    throw customExceptions;
                }
            }
            catch (CustomExceptions e){
                    // if file isn't csv extension
                    new CustomExceptions().fileFormatExceptionHandling();
                }




        }
        return invoiceHeaders;
    }


    public void writeInvoiceHeaderFile(ArrayList<InvoiceHeader> invoiceHeaders) throws IOException {

        this.invoiceHeaders = invoiceHeaders;
        JFileChooser invoiceHeaderChooser = new JFileChooser();
        int result = invoiceHeaderChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String filePath =  invoiceHeaderChooser.getSelectedFile().getPath();

            // Check if file path is null , if true throw an exception
            try {
                if (filePath == null || filePath.length() == 0)
                {
                    CustomExceptions customExceptions = new CustomExceptions();
                    throw  customExceptions;
                }
            }
            catch (CustomExceptions e)
            {
                new CustomExceptions().filePathNotFoundHandling();
            }

            /*Check the File Format , if wrong , throw an exception*/
            try {



                /* Check the file format or extension*/

                // if file is csv extension
                if (filePath.substring(filePath.length() - 3).equalsIgnoreCase("csv")) {

                    File invoiceHeaderFile = new File(filePath);
                    FileWriter invoiceHeaderFileWriter = new FileWriter(invoiceHeaderFile);
                    BufferedWriter invoiceHeaderBufferedWriter = new BufferedWriter(invoiceHeaderFileWriter);

                    for (int i = 0; i < invoiceHeaders.size(); i++) {
                        invoiceHeaderBufferedWriter.write(String.valueOf(invoiceHeaders.get(i).getInvoiceNumber()));
                        invoiceHeaderBufferedWriter.append(",");
                        invoiceHeaderBufferedWriter.write(invoiceHeaders.get(i).getInvoiceDate());
                        invoiceHeaderBufferedWriter.append(",");
                        invoiceHeaderBufferedWriter.write(invoiceHeaders.get(i).getCustomerName());
                        invoiceHeaderBufferedWriter.append(",");
                        invoiceHeaderBufferedWriter.write(String.valueOf(invoiceHeaders.get(i).getInvoiceTotal()));
                        invoiceHeaderBufferedWriter.write("\n");

                    }

                    invoiceHeaderBufferedWriter.close();
                    invoiceHeaderFileWriter.close();

                }

                // if file isn't csv extension
                else {
                    CustomExceptions customExceptions = new CustomExceptions();
                    throw  customExceptions;
                }

            }
            catch (CustomExceptions e){
                // if file isn't csv extension
                new CustomExceptions().fileFormatExceptionHandling();
            }
        }


    }


    public ArrayList<InvoiceLine> readInvoiceLinesFile() throws IOException {

        invoiceLines = new ArrayList<>();
        JFileChooser invoiceLinesChooser = new JFileChooser();
        int result = invoiceLinesChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            String filePath =  invoiceLinesChooser.getSelectedFile().getPath();

            try {


                /* Check the file format or extension*/

                // if file is csv extension
                if (filePath.substring(filePath.length()-3).equalsIgnoreCase("csv")) {

                    File invoiceLinesFile = new File(filePath);
                    BufferedReader invoiceLinesFileReader = new BufferedReader(new FileReader(invoiceLinesFile));

                    while ((line = invoiceLinesFileReader.readLine()) != null)
                    {
                        arr = line.split(",");
                        invoiceLines.add(new InvoiceLine(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2]), Integer.parseInt(arr[3]),Double.parseDouble(arr[4])));
                    }
                }

                // if file isn't csv extension
                else {
                   CustomExceptions customExceptions = new CustomExceptions();
                   throw customExceptions;
                }


            }
            catch (CustomExceptions e){
                // if file isn't csv extension
                new CustomExceptions().fileFormatExceptionHandling();
            }

        }

        return invoiceLines;

    }


    public void writeInvoiceLinesFile(ArrayList<InvoiceLine> invoiceLines) throws IOException {

        this.invoiceLines = invoiceLines;
        JFileChooser invoiceLineChooser = new JFileChooser();
        int result = invoiceLineChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String filePath =  invoiceLineChooser.getSelectedFile().getPath();

            // Check if file path is null , if true throw an exception
            try {
                if (filePath == null || filePath.length() == 0)
                {
                    CustomExceptions customExceptions = new CustomExceptions();
                    throw  customExceptions;
                }
            }
            catch (CustomExceptions e)
            {
                new CustomExceptions().filePathNotFoundHandling();
            }

            /*Check the File Format , if wrong , throw an exception*/
            try {

                /* Check the file format or extension*/

                // if file is csv extension
                if (filePath.substring(filePath.length() - 3).equalsIgnoreCase("csv")) {
                    File invoiceLineFile = new File(filePath);
                    FileWriter invoiceLineFileWriter = new FileWriter(invoiceLineFile);
                    BufferedWriter invoiceLineBufferedWriter = new BufferedWriter(invoiceLineFileWriter);

                    for (int i = 0; i < invoiceLines.size(); i++) {

                        invoiceLineBufferedWriter.write(String.valueOf(invoiceLines.get(i).getInvoiceNumber()));
                        invoiceLineBufferedWriter.append(",");
                        invoiceLineBufferedWriter.write(invoiceLines.get(i).getItemName());
                        invoiceLineBufferedWriter.append(",");
                        invoiceLineBufferedWriter.write(String.valueOf(invoiceLines.get(i).getItemPrice()));
                        invoiceLineBufferedWriter.append(",");
                        invoiceLineBufferedWriter.write(String.valueOf(invoiceLines.get(i).getItemCount()));
                        invoiceLineBufferedWriter.write(",");
                        invoiceLineBufferedWriter.write(String.valueOf(invoiceLines.get(i).getInvoiceLineTotal()));
                        invoiceLineBufferedWriter.write("\n");

                    }

                    invoiceLineBufferedWriter.close();
                    invoiceLineFileWriter.close();

                }

                // if file isn't csv extension
                else {
                    CustomExceptions customExceptions = new CustomExceptions();
                    throw  customExceptions;
                }
            }
            catch (CustomExceptions e){
                // if file isn't csv extension
                new CustomExceptions().fileFormatExceptionHandling();
            }

        }

    }


}




