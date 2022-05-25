package com.staqueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author DELL
 */
public class Frame extends JFrame {

    StackFrame stackFrame;
    private static final int ROWS = 20;
    private static final int COLUMNS = 20;
    StackSetup stackSetup;

    QueueFrame queueFrame;
    MainWindow mainWindow;
    LinkedListFrame linkedListFrame;

    Frame() {
        super("STAQUEUE");
        super.getContentPane().setBackground(Color.BLACK);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTabbedPane tab = new JTabbedPane(); // For the tabs at the top

        JScrollPane scrollPane = new JScrollPane();

        stackSetup = new StackSetup();
        scrollPane.setViewportView(stackSetup);
        stackSetup.setPreferredSize(tab.getSize());

        tab.addTab("Stack", scrollPane);
        tab.setMnemonicAt(0, KeyEvent.VK_1);

        stackSetup.createStackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                stackSetup.isDynamicStack = stackSetup.dynamicStackYes.isSelected();
                stackSetup.numberOfStacks = 1;
                stackFrame = new StackFrame(1, stackSetup.isDynamicStack);
                scrollPane.setViewportView(stackFrame);
                tab.setComponentAt(0, scrollPane);

                stackFrame.stackMenu.settingsBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure? " +
                                "You will lose all the data in the stack(s). Press Yes to continue. " +
                                "Press No to cancel.", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            scrollPane.setViewportView(stackSetup);
                            tab.setComponentAt(0, scrollPane);
                        }
                    }
                });

            }
        });

        queueFrame = new QueueFrame();
        tab.addTab("Queue", queueFrame);
        tab.setMnemonicAt(1, KeyEvent.VK_2);

        linkedListFrame = new LinkedListFrame();
        tab.addTab("Linked List", linkedListFrame);
        tab.setMnemonicAt(2, KeyEvent.VK_3);

        BinaryTree tree = new BinaryTree();
    //    JPanel f = new JPanel();
    // //    JOptionPane.showMessageDialog(TheWindow.frame, "Hi, user. This program works typing some letters from your keyboard"
    // //            + "\nSo, the operations you can use are:"
    // //            + "\n a --- Add an integer number"
    // //            + "\n f --- Add from file"
    // //            + "\n s --- Search an integer number"
    // //            + "\n d --- Delete an integer number"
    // //            + "\n h --- Help (if you forgot this");
    //     f.add();
    //     // create and add an event handler for window closing event
    //     f.setBounds(50, 50, 700, 700);
    //     f.setVisible(true);
        tab.addTab("Binary Tree", new TheWindow(tree));
        tab.setMnemonicAt(3, KeyEvent.VK_4);

        BFSAlgorithm bfs = new BFSAlgorithm();
        bfs.setVisible(true);
        tab.addTab("BFS", bfs);
        tab.setMnemonicAt(4, KeyEvent.VK_5);

        KruskalsAlgorithm project = new KruskalsAlgorithm();
        project.setVisible(true);
        tab.addTab("Kruskal", project);

        // mainWindow = new MainWindow(ROWS, COLUMNS);
        // tab.addTab("Prims", mainWindow);
        // tab.setMnemonicAt(5, KeyEvent.VK_6);

        add(tab);

        setVisible(true);
    }
}
