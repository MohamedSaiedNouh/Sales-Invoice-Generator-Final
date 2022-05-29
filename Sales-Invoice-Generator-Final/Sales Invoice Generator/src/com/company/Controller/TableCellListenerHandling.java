package com.company.Controller;


import com.company.View.LeftSideView.InvoicesTable;
import com.company.View.RightSideView.InvoiceItemsTable;
import com.company.View.RightSideView.TextFieldsPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class TableCellListenerHandling extends AbstractAction {
    InvoicesTable invoicesTable = InvoicesTable.Singleton();
    TextFieldsPanel textFieldsPanel = TextFieldsPanel.Singleton();

    DefaultTableModel invoiceItemsTableModel;
    DefaultTableModel invoicesTableModel;
    JTable invoiceItemsTable;
    static double invoiceTotal = 0.0;



    public TableCellListenerHandling(JTable invoiceItemsTable) {
        this.invoiceItemsTable = invoiceItemsTable;
        invoiceItemsTableModel = (DefaultTableModel) invoiceItemsTable.getModel();
        invoicesTableModel     = invoicesTable.getTableModel();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        TableCellListener tableCellListener = (TableCellListener) e.getSource();

        int colNumber = tableCellListener.getColumn();
        int rowNumber = tableCellListener.getRow();


        if ((colNumber == 3) && (tableCellListener.getNewValue() != null) && (invoiceItemsTableModel.getValueAt(rowNumber, colNumber - 1) != null)) {

            // Get the values of col2(item Price) and col3(item Count)
            String invoiceNumber = (String) invoiceItemsTableModel.getValueAt(rowNumber, 0);
            String col2Val = (String) invoiceItemsTableModel.getValueAt(rowNumber, 2);
            String col3Val = (String) invoiceItemsTableModel.getValueAt(rowNumber, 3);
            double itemPrice = Double.parseDouble(col2Val);
            int itemCount = Integer.parseInt(col3Val);

            // Update item Total
            double itemTotal = itemPrice * itemCount;
            invoiceItemsTableModel.setValueAt(itemTotal, rowNumber, 4);

            // Get the value of invoice total label
            double total = textFieldsPanel.getInvoiceTotal();


            /* Add new Row if actual row is the last one and this is a new invoice
               or
               Add new Row if actual row is the last one but this is an updating for last invoice
            */

            // Both will have the same logic to compute invoice total  */

            if ((invoiceItemsTableModel.getRowCount()) == (rowNumber+1))
            {
                // Get last updated invoice total
                invoiceTotal = textFieldsPanel.getInvoiceTotal();
                // Update invoice Total for this case
                invoiceTotal += itemTotal;

                // Add new Row once the actual one is filled
                invoiceItemsTableModel.addRow(new String[]{
                        (String.valueOf(invoiceNumber)), null, null, null, null
                });

                // Select the new row item cell
                invoiceItemsTable.setRowSelectionInterval(rowNumber + 1, rowNumber + 1);
                invoiceItemsTable.setColumnSelectionInterval(1, 1);
                invoiceItemsTable.setRowSelectionAllowed(true);
                invoiceItemsTable.setCellSelectionEnabled(true);

            }

            /* if Actual row is not the last one in case of updating exist invoice */
            else if ((invoiceItemsTableModel.getRowCount()) != (rowNumber+1))
            {
                // Get last updated invoice total
                invoiceTotal = textFieldsPanel.getInvoiceTotal();
                // Update invoice Total for this case
                invoiceTotal += itemTotal;

            }

            // Update invoice total label
            textFieldsPanel.setInvoiceTotalLabel(String.valueOf(invoiceTotal));

            // Update invoice header total cell
            int rowCount = invoicesTableModel.getRowCount();
            int i = 0;

            while (i < rowCount) {
                if (invoicesTableModel.getValueAt(i, 0).toString().equals(invoiceItemsTableModel.getValueAt(rowNumber, 0).toString()))
                {
                    invoicesTableModel.setValueAt(invoiceTotal, i, 3);
                }
                    i++;
                }





        }

    }

}
