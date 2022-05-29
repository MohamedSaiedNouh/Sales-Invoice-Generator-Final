package com.company.View.LeftSideView;

import com.company.Controller.ActionListenerHandling;
import com.company.View.RightSideView.TextFieldsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftButtons extends JPanel {

    ActionListener listener;


    public LeftButtons() {
        JButton newInvoiceButton = new JButton("Create New Invoice");
        JButton deleteInvoice = new JButton("Delete Invoice");

        add(newInvoiceButton);
        add(deleteInvoice);

        // Action Listeners Handling
        listener = new ActionListenerHandling();
        newInvoiceButton.setActionCommand("CreateNewInvoice");
        deleteInvoice.setActionCommand("DeleteInvoice");
        newInvoiceButton.addActionListener(listener);
        deleteInvoice.addActionListener(listener);


    }


}
