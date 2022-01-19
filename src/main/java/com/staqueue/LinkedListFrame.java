package com.staqueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

/**
 *
 * @author DELL
 */
public class LinkedListFrame extends JPanel {

    Stack choiceStack;
    boolean isRandom;
    LinkedListDisplay linkedDisplay;
    JPanel linkedDisplayPanel;
    LinkedListMenu linkedMenu;
    JPanel linkedMenuPanel;
    JPanel linkedPanel;
    JLabel linkedMessage;
    JPanel linkedMessagePanel;

    public LinkedListFrame() {

        choiceStack = new Stack(3);
        isRandom = false;

        JScrollPane scrollPane = new JScrollPane();

        linkedPanel = new JPanel(new BorderLayout());

        // Queue title and styling
        JLabel qTitle = new JLabel("<HTML><U>DATA STRUCTURES : DEMONSTRATION OF THE LINKED LIST</U></HTML>");
        qTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 28));
        qTitle.setForeground(Color.yellow);
        qTitle.setPreferredSize(new Dimension(800, 80));

        JPanel qTitlePanel = new JPanel();
        qTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 350, 0));
        qTitlePanel.setBackground(Color.darkGray);
        qTitlePanel.add(qTitle);

        // Queue message panel and styling
        linkedMessage = new JLabel(">>>Welcome to Linked List Demo. An Linked List queue has been made.");
        linkedMessage.setForeground(Color.white);
        linkedMessage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));

        linkedMessagePanel = new JPanel();
        linkedMessagePanel.setBackground(Color.BLACK);
        linkedMessagePanel.add(linkedMessage);

        // Queue menu and styling
        linkedMenu = new LinkedListMenu();
        linkedMenu.setBackground(Color.DARK_GRAY);

        linkedMenuPanel = new JPanel();
        linkedMenuPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        linkedMenuPanel.setBackground(Color.darkGray);
        linkedMenu.setPreferredSize(new Dimension(1350, 100));
        linkedMenuPanel.add(linkedMenu);
        linkedMenuPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        Box topDisplay = Box.createVerticalBox();
        topDisplay.add(qTitlePanel);
        topDisplay.add(linkedMessagePanel);
        topDisplay.add(linkedMenuPanel);

        linkedPanel.add(topDisplay, BorderLayout.NORTH);

        // Queue display and styling
        linkedDisplay = new LinkedListDisplay();
        linkedDisplayPanel = new JPanel();
        linkedDisplayPanel.add(Box.createRigidArea(new Dimension(0, 425)));
        linkedDisplayPanel.add(linkedDisplay);
        linkedDisplayPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));

        Box linkedDisplayBox = Box.createVerticalBox();
        linkedDisplayBox.add(linkedDisplayPanel);

        scrollPane.setViewportView(linkedDisplayBox);
        scrollPane.setVisible(true);

        linkedPanel.add(scrollPane, BorderLayout.CENTER);

        super.add(linkedPanel);

        // Interface to set the text in Queue Message Panel
        linkedDisplay.textSetter = new TextInterface() {
            public void setText(String str) {
                linkedMessage.setText(">>>" + str);
            }
        };

        addQueueActionListeners(); // Function to add actionlisteners to queue menu buttons
    }

    private void addQueueActionListeners() {

        // Queue Insert button on click actionlistener
        linkedMenu.insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String str = linkedMenu.text.getText();
                linkedMenu.text.setValue(null);
                if (isNum(str)) {
                    str = str.replace(",", "");
                    int num = Integer.parseInt(str);
                    linkedDisplay.update_insert(num, choiceStack);
                    linkedMenu.resetButton.setEnabled(true);
                    linkedMenu.resetEnabled = true;
                } else {
                    linkedDisplay.textSetter.setText("Invalid number");
                }
                if (choiceStack.top != -1) {
                    linkedMenu.undoButton.setEnabled(true);
                    linkedMenu.undoEnabled = true;
                }
            }
        });

        // Queue Delete button onclick actionlistener
        linkedMenu.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String selected = (String)linkedMenu.deleteCombo.getSelectedItem();
                String str = linkedMenu.text.getText();
                linkedMenu.text.setValue(null);
                if (isNum(str)) {
                    str = str.replace(",", "");
                    int num = Integer.parseInt(str);
                    linkedDisplay.update_delete(choiceStack,selected,num);
                    linkedMenu.resetButton.setEnabled(true);
                    linkedMenu.resetEnabled = true;
                } else {
                    linkedDisplay.textSetter.setText("Invalid number");
                }
                if (choiceStack.top != -1) {
                    linkedMenu.undoButton.setEnabled(true);
                }
            }
        });

        // Queue undo button onclick actionlistener
        linkedMenu.undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(choiceStack.nElts == 0){
                    linkedMessage.setText(">>>Cannot undo any more operations on Linked List.Perform some other operation and try again.");
                    return;
                }
                int choice = choiceStack.pop();
                System.out.println(choiceStack.nElts);
                if (choice == 0) {
                    //Recent operation was push
                    linkedDisplay.undoInsert();
                } else {
                    //Recent operation was pop
                    linkedDisplay.undoDelete();
                }
                linkedPanel.updateUI();
            }
        });

        // Queue random button onclick actionlistener
        // linkedMenu.randomButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent ae) {
        // if (!isRandom) {
        // isRandom = true;
        // linkedMenu.randomButton.setText("Stop");
        // linkedMenu.text.setEnabled(false);
        // linkedMenu.insertButton.setEnabled(false);
        // linkedMenu.deleteButton.setEnabled(false);
        // linkedMenu.undoButton.setEnabled(false);
        // linkedMenu.resetButton.setEnabled(false);
        // startQueueRandom();
        // } else {
        // isRandom = false;
        // linkedMenu.randomButton.setText("Random");
        // linkedMenu.text.setEnabled(true);
        // linkedMenu.insertButton.setEnabled(true);
        // linkedMenu.deleteButton.setEnabled(true);
        // linkedMenu.undoButton.setEnabled(linkedMenu.undoEnabled);
        // linkedMenu.resetButton.setEnabled(linkedMenu.resetEnabled);
        // }
        // }
        // });

        // Queue reset button onclick actionlistener
        linkedMenu.resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                linkedDisplay.reset();
                choiceStack.top = -1;
                linkedMenu.undoButton.setEnabled(false);
                linkedMenu.undoEnabled = false;
                linkedMenu.resetButton.setEnabled(false);
                linkedMenu.resetEnabled = false;
                linkedPanel.updateUI();
            }
        });
    }

    // Function to implement automatic random operations in Queue
    // private void startQueueRandom() {
    //     SwingWorker<Void, Void> q_random;
    //     q_random = new SwingWorker<Void, Void>() {
    //         protected Void doInBackground() throws Exception {
    //             if (linkedDisplay.q.front == linkedDisplay.q.size) {
    //                 linkedDisplay.textSetter.setText(
    //                         "Cannot perform any more operations on the queue. Please try using the undo or the reset buttons to continue. Front = "
    //                                 + Integer.toString(linkedDisplay.q.front) + " Rear = "
    //                                 + Integer.toString(linkedDisplay.q.rear));
    //                 linkedMenu.randomButton.doClick();
    //                 return null;
    //             }
    //             Thread.sleep(100);
    //             linkedDisplay.random(choiceStack);
    //             if (choiceStack.top != -1) {
    //                 linkedMenu.undoEnabled = true;
    //             }
    //             linkedMenu.resetEnabled = true;
    //             linkedPanel.updateUI();
    //             Thread.sleep(750);
    //             return null;
    //         }

    //         protected void done() {
    //             if (isRandom) {
    //                 startQueueRandom();
    //             }
    //         }
    //     };
    //     q_random.execute();
    // }

    // Function to check if the input string is a valid number or not
    private static boolean isNum(String str) {
        str = str.replace(",", "");
        try {
            Integer x = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
