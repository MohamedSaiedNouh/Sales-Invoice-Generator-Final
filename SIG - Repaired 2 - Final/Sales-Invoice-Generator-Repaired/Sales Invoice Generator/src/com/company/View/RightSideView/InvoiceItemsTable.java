package com.company.View.RightSideView;

import com.company.Controller.TableCellListener;
import com.company.Controller.TableCellListenerHandling;
import com.company.Model.InvoiceLine;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class InvoiceItemsTable extends JPanel   {

    private static final String[] cols = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    private static DefaultTableModel invoicesItemsTableModel;
    private static JTable invoicesItemsTable;
    private static InvoiceItemsTable InvoiceItemsTableSingleInstance = null;
    TableCellListener tableCellListener;
    Action action ;


    private InvoiceItemsTable() {

        invoicesItemsTableModel = new DefaultTableModel(cols, 0);
        invoicesItemsTable = new JTable(invoicesItemsTableModel);
        action = new TableCellListenerHandling(invoicesItemsTable);
        tableCellListener = new TableCellListener(invoicesItemsTable,action);


        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Invoices Items", TitledBorder.LEFT,
                TitledBorder.TOP));
        add(new JScrollPane(invoicesItemsTable));



    }

    public static InvoiceItemsTable Singleton() {
        if (InvoiceItemsTableSingleInstance == null) {
            InvoiceItemsTableSingleInstance = new InvoiceItemsTable();
        }
        return InvoiceItemsTableSingleInstance;

    }

    public void addNewLine(int newInvoiceNumber) {
        invoicesItemsTableModel.addRow(new String[]{String.valueOf(newInvoiceNumber), null, null, null, null});


    }


    public DefaultTableModel getModel() {
     return (DefaultTableModel) invoicesItemsTable.getModel();
    }


    public ArrayList<InvoiceLine> readLines() {
        ArrayList<InvoiceLine> invoiceLineArrayList = new ArrayList<>();
        long numOfRows = invoicesItemsTableModel.getRowCount();
//        long numOfCols = invoicesItemsTableModel.getColumnCount();
        System.out.println(numOfRows);

        String[] arr = new String[5];

        for (int i = 0 ; i< (numOfRows);i++)
        {
            for (int j=0; j<5;j++)
            {

                arr[j] = invoicesItemsTableModel.getValueAt(i, j).toString();
            }

            invoiceLineArrayList.add(new InvoiceLine(Integer.parseInt(arr[0]),arr[1], Double.parseDouble(arr[2]),Integer.parseInt(arr[3]),Double.parseDouble(arr[4])));

        }


        return invoiceLineArrayList;
    }


    public void deleteAllRows() {
        invoicesItemsTableModel.setRowCount(0);
    }

    public int getSelectedRow() {
        return (invoicesItemsTable.getSelectedRow());
    }

    public void removeRow(int row) {
        invoicesItemsTableModel.removeRow(row);
    }


    public void deleteLastRow() {
        int numOfRows = invoicesItemsTableModel.getRowCount();
        Object val = invoicesItemsTableModel.getValueAt(numOfRows-1,1);

        // Delete if Item Name cell is empty
        if (val == null){
            invoicesItemsTableModel.removeRow(numOfRows-1);
        }
    }

    public void writeRows(ArrayList<InvoiceLine> readInvoiceLines) {
        // Clear Table Contents

        invoicesItemsTableModel.setRowCount(0);

        // Refill the table with Arraylist Data

        for (int i = 0; i < readInvoiceLines.size(); i++) {
            int invoiceNumber = readInvoiceLines.get(i).getInvoiceNumber();
            String itemName = readInvoiceLines.get(i).getItemName();
            double itemPrice = readInvoiceLines.get(i).getItemPrice();
            int itemCount = readInvoiceLines.get(i).getItemCount();
            double invoiceLineTotal = readInvoiceLines.get(i).getInvoiceLineTotal();

            Object[] objs = {invoiceNumber, itemName, itemPrice, itemCount,invoiceLineTotal};
            invoicesItemsTableModel.addRow(objs);
        }

    }

    public ArrayList<InvoiceLine> readRows() {

        ArrayList<InvoiceLine> invoiceLineArrayList = new ArrayList<>();
        long numOfRows = invoicesItemsTableModel.getRowCount();
        long numOfCols = invoicesItemsTableModel.getColumnCount();
        String[] arr = new String[(int) numOfCols];

        for (int i = 0 ; i< numOfRows;i++)
        {
            for (int j=0; j<numOfCols;j++)
            {
                arr[j] = invoicesItemsTableModel.getValueAt(i,j).toString();

            }

            invoiceLineArrayList.add(new InvoiceLine(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2]), Integer.parseInt(arr[3]),Double.parseDouble(arr[4])));

        }

        return invoiceLineArrayList;

    }

    public int getLastInvoiceNumber() {
        int numOfRows = invoicesItemsTableModel.getRowCount();
        if (numOfRows == 0) {
            return 1;
        } else {
            String invoiceNumber = invoicesItemsTableModel.getValueAt(numOfRows - 1, 0).toString()  ;

            return (  Integer.valueOf(invoiceNumber) );
        }
    }

    public Object getValueAt(int row, int col) {
        if (invoicesItemsTableModel.getValueAt(row,col) == null)
        {
            return 0;
        }
        return invoicesItemsTableModel.getValueAt(row,col);

    }

    public void insertNewRow(int row, int invoiceNumber) {
        invoicesItemsTableModel.insertRow(row,new String[]{String.valueOf(invoiceNumber), null, null, null, null});
    }



    public void setRowColumnSelectionInterval(int row1, int row2, int col1, int col2) {
        invoicesItemsTable.setRowSelectionInterval(row1,row2);
        invoicesItemsTable.setColumnSelectionInterval(col1,col2);
        invoicesItemsTable.setRowSelectionAllowed(true);
        invoicesItemsTable.setCellSelectionEnabled(true);
    }

    public void setRowCount() {
        invoicesItemsTableModel.setRowCount(0);
    }

    public int getRowCount() {
        return (invoicesItemsTableModel.getRowCount());
    }
}







