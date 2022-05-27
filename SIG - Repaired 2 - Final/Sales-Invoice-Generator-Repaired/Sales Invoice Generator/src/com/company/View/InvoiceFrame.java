package com.company.View;

import com.company.Controller.ActionListenerHandling;
import com.company.View.LeftSideView.LeftPanel;
import com.company.View.RightSideView.RightPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class InvoiceFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;

    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    ActionListener listener;
    public InvoiceFrame()
    {
        super("Invoice Generator");

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadMenuItem = new JMenuItem("Load Files");
        saveMenuItem = new JMenuItem("Save Files");


        menuBar.add(fileMenu);
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        setJMenuBar(menuBar);

        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();



      JSplitPane splitPane = new JSplitPane();
      splitPane.setDividerSize(0);
      splitPane.setDividerLocation(650);
      splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
      splitPane.setLeftComponent(leftPanel);
      splitPane.setRightComponent(rightPanel);
      this.add(splitPane);



        setSize(1500,1500);
        setLocation(100,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // Action Listeners Handling
        listener = new ActionListenerHandling();
        loadMenuItem.setActionCommand("LoadFiles");
        saveMenuItem.setActionCommand("SaveFiles");
        loadMenuItem.addActionListener(listener);
        saveMenuItem.addActionListener(listener);


    }


}

