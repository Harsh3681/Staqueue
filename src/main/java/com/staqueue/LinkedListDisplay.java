package com.staqueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class LinkedListDisplay extends JPanel {
    public LinkedList linkedList;
    public TextInterface textSetter;
    public static Boolean q_isRandom;
    public ArrayList list;
    public Box rowBox;
    public int array[];
    public Stack popStack;
    private JLabel headLabel;
    
    LinkedListDisplay() {
        super();
        super.setLayout(new BorderLayout());
        
        popStack = new Stack(3,3); //stack used to store recently popped elements, for undo
        headLabel = new JLabel("HEAD-->"); //Label to point to top element in gui
        headLabel.setFont(new Font("Serif", Font.BOLD, 12)); 

        linkedList = new LinkedList();
        rowBox = Box.createHorizontalBox();
        rowBox.add(Box.createRigidArea(new Dimension(60,0)));
        list = new ArrayList();
        StackElement elt = new StackElement();
        elt.topPanel.add(headLabel);
        elt.topPanel.setVisible(true);
        elt.eltPanel.setBorder(null);
        list.add(elt);
        rowBox.add((StackElement)list.get(0));
        super.add(rowBox,BorderLayout.CENTER);
    }
    
    public void update_insert(int num,Stack undo) {
        if (undo.top == undo.size - 1) {
            undo.forgetEarlierChoice();
        }
        undo.push(0);
        linkedList =  linkedList.insert(linkedList,num);
        linkedList.printList(linkedList);
        StackElement checkNull = (StackElement)list.get(list.size()-1);
        if(checkNull.elt.getText().equals("NULL")) {
            list.remove(checkNull);
            rowBox.remove(checkNull);
        }
        StackElement elt = new StackElement(num,num);
        list.add(elt);
        rowBox.add((StackElement)list.get(list.size()-1),-1);
        StackElement elt1 = new StackElement("null");
        list.add(elt1);
        rowBox.add((StackElement)list.get(list.size()-1),-1);
        // elts[linkedList.rear + 1].setText(Integer.toString(num));
        textSetter.setText(Integer.toString(num)+" has been inserted to the Linked List");
    }
    
    public void update_delete(Stack undo,String selected,int num){
        if(linkedList.size(linkedList) == 0 && list.size() == 1){
            textSetter.setText("Linked List is empty! Cannot delete any element.");
            return;
        }

        if (undo.top == undo.size - 1) {
            undo.forgetEarlierChoice();
        }
        if (popStack.nElts == popStack.size) {
            popStack.forgetEarlyrChoice(); //Deleting elements popped earlier from temporary stack
        }
        undo.push(1);
        // frontPanel[q.front + 1].remove(front);
        // elts[q.front + 1].setText("");
        // int num=q.del();
        int value,index=0;
        if(selected == "By Position"){
            index = num-1;
            value = linkedList.getElement(linkedList, index);
            linkedList = linkedList.deleteAtPosition(linkedList,num);
            if(index != 100){
                StackElement elt = (StackElement)list.get(index+1);
                list.remove(index+1);
                array = new int[2];
                array[0] = value;
                array[1] = index;
                popStack.push(array);
                rowBox.remove(elt);
                
                textSetter.setText(Integer.toString(elt.val) + " has been deleted from Linked List ");
            }
        }
        else{
            value = num;
            index = linkedList.getIndex(linkedList,num);
            linkedList = linkedList.deleteByKey(linkedList, num);
            if(index != 100){
                StackElement elt = (StackElement)list.get(index+1);
                list.remove(index+1);
                array = new int[2];
                array[0] = value;
                array[1] = index;
                popStack.push(array);
                rowBox.remove(elt);
                
                textSetter.setText(Integer.toString(elt.val) + " has been deleted from Linked List ");
            }
        }
        
    
    }
    
    public void undoInsert(){
        linkedList.deleteAtPosition(linkedList, linkedList.size(linkedList)-1);
        list.remove(list.size()-1);
        rowBox.remove(1);
            
        textSetter.setText("Undo on Linked List successful.");
            
    }
    
    public void undoDelete(){
        int array[] = popStack.popArr();
        int value = array[0];
        int index = array[1];
        System.out.println(value + " " + index);
        StackElement elt = new StackElement(value,value);
            list.add(elt);
            rowBox.add((StackElement)list.get(list.size()-1),index+2);
            
            textSetter.setText("Undo on Linked List successful.");
    }
    
    // public void random(Stack undo){
    //     int choice;
    //     Random getRandom=new Random();
    //     if(linkedList.rear==linkedList.size(linkedList) - 2) choice=1;
    //     else if(linkedList.front > linkedList.rear) choice=0;
    //     else{
    //         int r=getRandom.nextInt(10+this.linkedList.nElts);
    //         if(r<5) choice=0;
    //         else choice=1; 
    //     }
    //     if(choice==1) update_delete(undo);
    //     else update_insert(getRandom.nextInt(100),undo);
    // }
    
    public void reset(){
        linkedList = new LinkedList();
        list.clear();
            
        StackElement elt = new StackElement();
        elt.topPanel.add(headLabel);
        elt.topPanel.setVisible(true);
        elt.eltPanel.setBorder(null);
        list.add(elt);
            
        rowBox.removeAll();
        rowBox.add(Box.createRigidArea(new Dimension(0,60)));
        
        rowBox.add((StackElement)list.get(0));
        textSetter.setText("Linked List has been reset.");
        
    }
}
