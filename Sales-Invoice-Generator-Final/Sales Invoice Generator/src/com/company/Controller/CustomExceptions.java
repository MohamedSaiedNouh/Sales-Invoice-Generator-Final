package com.company.Controller;

import com.company.View.LeftSideView.InvoicesTable;

import javax.swing.*;

public class CustomExceptions extends Exception{

    private static CustomExceptions customExceptionsSingleInstance = null;


    public CustomExceptions(){}


    public void fileFormatExceptionHandling()
    {
        JOptionPane.showMessageDialog(null,"Wrong File Format","Wrong File Format Error Message",JOptionPane.ERROR_MESSAGE);
    }

    public void filePathNotFoundHandling()
    {
        JOptionPane.showMessageDialog(null,"Folder/File path is not found","Folder/File path is not found Error Message",JOptionPane.ERROR_MESSAGE);
    }

}
