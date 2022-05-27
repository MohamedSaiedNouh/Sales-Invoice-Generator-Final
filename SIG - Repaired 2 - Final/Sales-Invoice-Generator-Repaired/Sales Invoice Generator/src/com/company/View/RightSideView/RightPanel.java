package com.company.View.RightSideView;

import com.company.Controller.ActionListenerHandling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {


    private JButton    saveButton;
    private JButton    cancelButton;
    ActionListener listener;



    public RightPanel()
    {
        JPanel rightPanel = new JPanel();

//        TextFieldsPanel rightTextFieldsPanel = new TextFieldsPanel() ;
        TextFieldsPanel rightTextFieldsPanel = TextFieldsPanel.Singleton();

//        InvoiceItemsTable rightTablePanel = new InvoiceItemsTable();
        InvoiceItemsTable rightTablePanel = InvoiceItemsTable.Singleton();
        RightButtons rightButtonsPanel = new RightButtons();

        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.setBackground(null);
//        rightPanel.setPreferredSize(new Dimension(500, 500));



        rightPanel.add(rightTextFieldsPanel);
        rightPanel.add(rightTablePanel);
        rightPanel.add(rightButtonsPanel);


        add(rightPanel);




    }

}
