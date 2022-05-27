package com.company.View.LeftSideView;

import com.company.Model.InvoiceHeader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class InvoicesTable extends JPanel {
    private static final String[] cols = {"No.", "Date", "Customer", "Total"} ;

    private static DefaultTableModel invoicesTableModel;
    private static JTable invoicesTable;
    private static InvoicesTable invoicesTableSingleInstance = null;

    private InvoicesTable() {

        invoicesTableModel = new DefaultTableModel(cols,0);
        invoicesTable = new JTable(invoicesTableModel);
       this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Invoices Table", TitledBorder.LEFT,
                TitledBorder.TOP));
        add(new JScrollPane(invoicesTable));


    }

    public static InvoicesTable Singleton()
    {
        if (invoicesTableSingleInstance == null)
        {
            invoicesTableSingleInstance = new InvoicesTable();
        }
        return invoicesTableSingleInstance;
    }



    public ArrayList<InvoiceHeader> readRows() {
        ArrayList<InvoiceHeader> invoiceHeaderArrayList = new ArrayList<>();
        long numOfRows = invoicesTableModel.getRowCount();
        long numOfCols = invoicesTableModel.getColumnCount();
        String[] arr = new String[(int) numOfCols];

        for (int i = 0 ; i< numOfRows;i++)
        {
            for (int j=0; j<numOfCols;j++)
            {
                arr[j] = invoicesTableModel.getValueAt(i,j).toString();

            }

            invoiceHeaderArrayList.add(new InvoiceHeader(Integer.parseInt(arr[0]),arr[1], arr[2], Double.parseDouble(arr[3])));

        }

        return invoiceHeaderArrayList;


    }


    public void writeRows(ArrayList<InvoiceHeader> readInvoiceHeader)
    {
        // Clear Table Contents

        invoicesTableModel.setRowCount(0);

        // Refill the table with Arraylist Data

        for (int i = 0; i < readInvoiceHeader.size(); i++) {
            int invoiceNumber = readInvoiceHeader.get(i).getInvoiceNumber();
            String invoiceDate = readInvoiceHeader.get(i).getInvoiceDate();
            String invoiceCustomer = readInvoiceHeader.get(i).getCustomerName();
            double invoiceTotal = readInvoiceHeader.get(i).getInvoiceTotal();

            Object[] objs = {invoiceNumber, invoiceDate, invoiceCustomer, invoiceTotal};
            invoicesTableModel.addRow(objs);
        }
    }

    public int  getLastInvoiceNumber () {
        int numOfRows = invoicesTableModel.getRowCount();
        if (numOfRows == 0) {
            return 1;
        } else {
            String invoiceNumber = invoicesTableModel.getValueAt(numOfRows - 1, 0).toString()  ;

            return (  Integer.valueOf(invoiceNumber)+1  );
        }
    }


    public int getSelectedRow() {
        return (invoicesTable.getSelectedRow());
    }

    public void removeRow(int selectedRow) {
        // Remove Selected Row
        invoicesTableModel.removeRow(selectedRow);
    }


    public void addNewHeader(int newInvoiceNumber) {
        // Add new Row (Header)
        invoicesTableModel.addRow(new String[]{String.valueOf(newInvoiceNumber), null, null, null, null});
        // Initialize Invoice Total Cell by Zero
        invoicesTableModel.setValueAt(0.0,invoicesTableModel.getRowCount()-1,3);

    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) invoicesTable.getModel();
    }


    public Object getValueAt(int row, int col ) {
        return  invoicesTableModel.getValueAt(row,col);
    }

    public int getRowCount() {
        return (invoicesTableModel.getRowCount());
    }

    public void setValueAt(double val, int row, int col) {
        invoicesTableModel.setValueAt(val,row,col);
    }

    public void setRowCount() {
        invoicesTableModel.setRowCount(0);
    }
}
