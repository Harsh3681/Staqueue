package com.staqueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author DELL
 */
public class LinkedListMenu extends JPanel {
    public JComboBox insertCombo;
    public JComboBox deleteCombo;
    public JButton deleteButton;
    public JButton insertButton;
    public JButton resetButton;
    public JButton undoButton;
    public JButton randomButton;
    public JFormattedTextField text,keyText;
    public boolean undoEnabled, resetEnabled;

    public LinkedListMenu() {

        super();

        Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border titled = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        titled = BorderFactory.createTitledBorder(titled, "Linked List Menu", TitledBorder.CENTER,
                TitledBorder.DEFAULT_JUSTIFICATION,
                new Font("Serif", Font.PLAIN, 15), Color.black);
        Border line = BorderFactory.createLineBorder(Color.darkGray);
        Border compound = BorderFactory.createCompoundBorder(titled, empty);
        super.setBorder(BorderFactory.createCompoundBorder(compound, line));

        Box linkedBox = Box.createHorizontalBox();

        JPanel insertPanel = new JPanel();
        insertPanel.setLayout(new FlowLayout());

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        text = new JFormattedTextField(formatter);
        text.setPreferredSize(new Dimension(80, 30));
        keyText = new JFormattedTextField(formatter);
        keyText.setPreferredSize(new Dimension(80, 30));
        String s[] = { "At Position", "At Beginning", "At end" };
        insertCombo = new JComboBox(s);
        insertCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                JComboBox source = (JComboBox)ae.getSource();
                String selected = (String) source.getSelectedItem(); 
                if(selected == "At Position"){
                    keyText.setVisible(true);
                } 
                else{
                    keyText.setVisible(false);
                }
            }
        });;
        insertCombo.setPreferredSize(new Dimension(150, 30));
        insertButton = new JButton("Insert");
        insertButton.setPreferredSize(new Dimension(70, 30));
        insertPanel.add(text);
        insertPanel.add(insertButton);
        insertPanel.setBackground(Color.lightGray);
        linkedBox.add(insertPanel);
        String d[] = { "By Position", "By Key" };
        deleteCombo = new JComboBox(d);
        deleteCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                JComboBox source = (JComboBox)ae.getSource();
                String selected = (String) source.getSelectedItem(); 
                keyText.setVisible(false);
            }
        });

        deleteCombo.setMinimumSize(new Dimension(150, 30));
        deleteCombo.setPreferredSize(deleteCombo.getMinimumSize());
        deleteButton = new JButton("Delete");
        deleteButton.setMinimumSize(new Dimension(150, 30));
        deleteButton.setPreferredSize(deleteButton.getMinimumSize());

        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new FlowLayout());
        deletePanel.add(deleteCombo);
        deletePanel.add(deleteButton);
        deletePanel.setBackground(Color.lightGray);
        linkedBox.add(deletePanel);

        // randomButton=new JButton("Random");
        // randomButton.setMinimumSize(new Dimension(150,30));
        // randomButton.setPreferredSize(randomButton.getMinimumSize());

        // JPanel randomPanel=new JPanel();
        // randomPanel.setLayout(new FlowLayout());
        // //randomPanel.add(randomButton);
        // randomPanel.setBackground(Color.lightGray);
        // linkedBox.add(randomPanel);

        undoButton = new JButton("Undo");
        undoButton.setMinimumSize(new Dimension(150, 30));
        undoButton.setPreferredSize(undoButton.getMinimumSize());

        JPanel undoPanel = new JPanel();
        undoPanel.setLayout(new FlowLayout());
        undoButton.setEnabled(false);
        undoPanel.add(undoButton);
        undoPanel.setBackground(Color.lightGray);
        linkedBox.add(undoPanel);

        resetButton = new JButton("Reset");
        resetButton.setMinimumSize(new Dimension(150, 30));
        resetButton.setPreferredSize(resetButton.getMinimumSize());

        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new FlowLayout());
        resetButton.setEnabled(false);
        resetPanel.add(resetButton);
        resetPanel.setBackground(Color.lightGray);
        linkedBox.add(resetPanel);

        super.add(linkedBox);
    }
}
