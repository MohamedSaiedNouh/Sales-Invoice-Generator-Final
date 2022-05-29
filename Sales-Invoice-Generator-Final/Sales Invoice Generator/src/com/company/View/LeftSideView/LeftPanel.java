package com.company.View.LeftSideView;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    public LeftPanel()
    {
        JPanel leftPanel = new JPanel();

        // Create Invoices Table Singleton
        InvoicesTable leftTablePanel = InvoicesTable.Singleton();

        //        InvoicesTable leftTablePanel = new InvoicesTable();

        LeftButtons leftButtonsPanel = new LeftButtons();

        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
//        leftPanel.setPreferredSize(new Dimension(500, 1000));
        leftPanel.setBackground(null);

        leftPanel.add(leftTablePanel);
        leftPanel.add(leftButtonsPanel);

        add(leftPanel);

    }

}

