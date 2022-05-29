package com.company.Controller;

import com.company.Model.FileOperations;
import com.company.Model.InvoiceHeader;
import com.company.Model.InvoiceLine;
import com.company.View.LeftSideView.InvoicesTable;
import com.company.View.RightSideView.InvoiceItemsTable;
import com.company.View.RightSideView.TextFieldsPanel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ActionListenerHandling implements ActionListener  {

    FileOperations fileOperations = new FileOperations();
    InvoicesTable invoicesTable = InvoicesTable.Singleton();
    InvoiceItemsTable invoiceItemsTable = InvoiceItemsTable.Singleton();
    TextFieldsPanel textFieldsPanel = TextFieldsPanel.Singleton();

    static ArrayList<InvoiceHeader> writeInvoiceHeader = new ArrayList<>();
    static ArrayList<InvoiceHeader> readInvoiceHeader = new ArrayList<>();
    static ArrayList<InvoiceLine> writeInvoiceLines = new ArrayList<>();
    static ArrayList<InvoiceLine> readInvoiceLines = new ArrayList<>();

    static int newInvoiceNumber;
    static int newInvoiceButtonFlag = 0;



    @Override
    public void actionPerformed(ActionEvent e) {
        if      (e.getActionCommand().equals("LoadFiles")) {


            // Clear All Tables and Text Fields
            invoicesTable.setRowCount();
            invoiceItemsTable.setRowCount();
            textFieldsPanel.setInvoiceNumberLabel("");
            textFieldsPanel.setCustomerName("");
            textFieldsPanel.setInvoiceDate("");
            textFieldsPanel.setInvoiceTotalLabel("");


            /* Read the invoice header and invoices lines  file */

            try
            {
                JOptionPane.showMessageDialog(null,"Please select Invoice Header File","Please select Invoice Header File Information Message",JOptionPane.INFORMATION_MESSAGE);
                readInvoiceHeader = fileOperations.readInvoiceHeaderFile();
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null,"Wrong File Format","Wrong File Format Error Message",JOptionPane.ERROR_MESSAGE);
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null,"File Not Found","File not found Error Message",JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            try
            {
                JOptionPane.showMessageDialog(null,"Please select Invoice Line File","Please select Invoice Line File Information Message",JOptionPane.INFORMATION_MESSAGE);
                readInvoiceLines = fileOperations.readInvoiceLinesFile();
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null,"File not found","File not found Error Message",JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }


            // Update Invoices table and invoices lines table
                invoicesTable.writeRows(readInvoiceHeader);
                invoiceItemsTable.writeRows(readInvoiceLines);


        }

        else if (e.getActionCommand().equals("SaveFiles")) {

            // Delete Last Empty (if Found) Row when saving
            invoiceItemsTable.deleteLastRow();

            // Get the invoices headers from table
            writeInvoiceHeader = invoicesTable.readRows();
            writeInvoiceLines = invoiceItemsTable.readRows();

            // then Write them into invoices header file


            try {
                fileOperations.writeInvoiceHeaderFile(writeInvoiceHeader);
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null,"File Not Found","File not found Error Message",JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                fileOperations.writeInvoiceLinesFile(writeInvoiceLines);
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null,"File Not Found","File not found Error Message",JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }


        }

        else if (e.getActionCommand().equals("CreateNewInvoice")) {

            // Make the flag 1
            newInvoiceButtonFlag++;

            // Get the number of new invoice
             int newInvoiceNumber = invoicesTable.getLastInvoiceNumber();


            /* Clear invoice Date and customer name text fields*/
            textFieldsPanel.setInvoiceDate("");
            textFieldsPanel.setCustomerName("");

            // Add new Invoice Header
            invoicesTable.addNewHeader(newInvoiceNumber);



        }

        else if (e.getActionCommand().equals("DeleteInvoice")) {

            // Get the selected row index
            int selectedRow = invoicesTable.getSelectedRow();

            // Get the invoice number (invoices table)
            Object invoiceNumber =  invoicesTable.getValueAt(selectedRow,0);

            // Validate row selection then Delete the selected invoice header if valid
            if (selectedRow != -1) {
                invoicesTable.removeRow(selectedRow);
            }

            /* Delete invoice lines belong to deleted invoice header */
            // Get invoices items table count
            int invoicesItemsRowCount = invoiceItemsTable.getRowCount();

            // Local variable used as loop index
            int i=0;

            // Procedure to delete invoice lines of deleted invoice header
            while (i < (invoicesItemsRowCount))
            {

                if ((invoiceItemsTable.getValueAt(i,0)) == invoiceNumber )
                {
                    invoiceItemsTable.removeRow(i);
                    invoicesItemsRowCount = invoiceItemsTable.getRowCount();
                    i=-1;

                }
               i++;
            }
        }

        else if (e.getActionCommand().equals("CreateNewLine")) {

            // Check if valid line is selected
            int selectedRow = invoiceItemsTable.getSelectedRow();

            /* Get Invoice Date and Customer Name*/
            DefaultTableModel invoicesTableModel = invoicesTable.getTableModel();
            int rowCount = invoicesTableModel.getRowCount();
            String invoiceDate  = invoicesTableModel.getValueAt(rowCount-1,1).toString();
            String customerName = null;
            String invoiceTotal = null;
            int i = 0;


            /* Specify if adding new line for existing invoice or for new invoice
               by checking the flag */

            // New invoice header is added then new line will be added
            if (newInvoiceButtonFlag == 1 && selectedRow == -1 )
            {

                // New invoice header is added
                 newInvoiceNumber = invoicesTable.getLastInvoiceNumber()-1;
                // Add new line for this invoice
                invoiceItemsTable.addNewLine(newInvoiceNumber);

                /* Set invoice number , invoice Date , customer name and Clear Invoice Total */
                 invoiceTotal = invoicesTableModel.getValueAt(rowCount-1,3).toString();
                 customerName = invoicesTableModel.getValueAt(rowCount-1,2).toString();

                textFieldsPanel.setInvoiceNumberLabel(String.valueOf(newInvoiceNumber));
                textFieldsPanel.setInvoiceDate(invoiceDate);
                textFieldsPanel.setCustomerName(customerName);
                textFieldsPanel.setInvoiceTotalLabel(invoiceTotal);
            }

            // Adding new line for last existed invoice
            else if (newInvoiceButtonFlag == 0 && selectedRow == -1 )
            {
                // add new line for the last existed invoice
                 newInvoiceNumber = invoiceItemsTable.getLastInvoiceNumber();
                // Add new line
                invoiceItemsTable.addNewLine(newInvoiceNumber);


                // Get invoice total and customer name
                invoiceTotal = invoicesTableModel.getValueAt(rowCount-1,3).toString();
                customerName = invoicesTableModel.getValueAt(rowCount-1,2).toString();


                /* Set invoice number , invoice Date , customer name and Invoice Total */
                textFieldsPanel.setInvoiceNumberLabel(String.valueOf(newInvoiceNumber));
                textFieldsPanel.setInvoiceDate(invoiceDate);
                textFieldsPanel.setCustomerName(customerName);
                textFieldsPanel.setInvoiceTotalLabel(invoiceTotal);

            }

            //add new line after the selected line (add invoice line for the same invoice)
            else if (newInvoiceButtonFlag == 0 && selectedRow != -1)
            {

                // Get the value of invoice number to be set
                newInvoiceNumber = (int) invoiceItemsTable.getValueAt(selectedRow,0);
                // Insert new row after the selected row
                invoiceItemsTable.insertNewRow(selectedRow+1,newInvoiceNumber);
                // Select the item name cell
                invoiceItemsTable.setRowColumnSelectionInterval(selectedRow+1,selectedRow+1,1,1);

                while (i< (invoicesTableModel.getRowCount())) {
                    
                    if ( ((int)invoicesTableModel.getValueAt(i,0)) == newInvoiceNumber)
                    {
                        invoiceTotal = invoicesTableModel.getValueAt(i,3).toString();
                        customerName = invoicesTableModel.getValueAt(i,2).toString();
                    }
                    i++;
                }
                
                /* Set invoice number , invoice Date , customer name and Clear Invoice Total */
                textFieldsPanel.setInvoiceNumberLabel(String.valueOf(newInvoiceNumber));
                textFieldsPanel.setInvoiceDate(invoiceDate);
                textFieldsPanel.setCustomerName(customerName);
                textFieldsPanel.setInvoiceTotalLabel(invoiceTotal);

            }

            // Reset the flag
            newInvoiceButtonFlag =0;


        }

        else if (e.getActionCommand().equals("DeleteLine")){

            // Check if valid line is selected
            int selectedRow = invoiceItemsTable.getSelectedRow();

            // Get invoices table row count
            int invoicesTableRowCount = invoicesTable.getRowCount();

            // Get invoices items table row count
            int invoicesItemsTableRowCount = invoiceItemsTable.getRowCount();

            // Local Variable used as iteration index
            int i = 0;

            // Get invoice number of selected line
            String invoiceNumber = invoiceItemsTable.getValueAt(selectedRow,0).toString();

            // Get the item total cell value
            double itemTotal = Double.parseDouble(invoiceItemsTable.getValueAt(selectedRow,4).toString());

            /* Delete the selected Row and Update data */
            // Row selection is verified and item name cell is not null
            if (selectedRow != -1 && (invoiceItemsTable.getValueAt(selectedRow,1) != null) ) {


                // Delete selected line
                invoiceItemsTable.removeRow(selectedRow);

                // if the selected row is the first and last one
                if (selectedRow == 0)
                {

                    // Clear invoice number , invoice date , invoice total and customer name
                    textFieldsPanel.setInvoiceNumberLabel(" ");
                    textFieldsPanel.setInvoiceDate("");
                    textFieldsPanel.setCustomerName("");
                    textFieldsPanel.setInvoiceTotalLabel(" ");

                }


                // Iterate through invoices table rows till get the invoice header total should be updated
                while (i<invoicesTableRowCount)
                {
                    if(invoicesTable.getValueAt(i,0).toString().equals(invoiceNumber))
                    {
                        // Get current invoice total cell value
                        double currentInvoiceTotal = (double) invoicesTable.getValueAt(i,3);

                        // Compute new invoice total
                        double newInvoiceTotal = currentInvoiceTotal-itemTotal;

                        // Update invoice total cell
                        invoicesTable.setValueAt(newInvoiceTotal,i,3);

                        // if all invoice lines are removed , clear total label
                        if (newInvoiceTotal == 0.0 ) {
                            textFieldsPanel.setInvoiceTotalLabel("");
                        }
                        // Else set label by the new total value
                        else
                        {
                            textFieldsPanel.setInvoiceTotalLabel(String.valueOf(newInvoiceTotal));
                        }

                        break;

                    }

                    i++;
                }



            }

            // If the selected row is not filled yet and need to be deleted again
            else if (selectedRow != -1 && ( (invoiceItemsTable.getValueAt(selectedRow,1) == null)||(invoiceItemsTable.getValueAt(selectedRow,4) == null) ) )
            {
                // Clear invoice number , invoice date , invoice total and customer name
                invoiceItemsTable.removeRow(selectedRow);
                textFieldsPanel.setInvoiceNumberLabel(" ");
                textFieldsPanel.setInvoiceDate("");
                textFieldsPanel.setCustomerName("");
                textFieldsPanel.setInvoiceTotalLabel(" ");

            }








        }


    }



}
