package com.company.View.RightSideView;

import com.company.Controller.ActionListenerHandling;
import com.company.View.LeftSideView.InvoicesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RightButtons extends JPanel  {
    ActionListener listener;

    public RightButtons()
    {
        this.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Create New Line");
        JButton cancelButton = new JButton("Delete Line");
        add(saveButton);
        add(cancelButton);

        // Action Listeners Handling
        listener = new ActionListenerHandling();
        saveButton.setActionCommand("CreateNewLine");
        cancelButton.setActionCommand("DeleteLine");
        saveButton.addActionListener(listener);
        cancelButton.addActionListener(listener);

    }


}
