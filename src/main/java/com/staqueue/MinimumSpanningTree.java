package com.staqueue;

// Maddie London and Berke Nuri
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.util.Stack;
import javax.swing.JPanel;

public class MinimumSpanningTree extends JPanel
{
    BFSAlgorithm parent = null;
    
    ArrayList<Edge> edges;
    ArrayList<Edge> graphEdges;
    ArrayList<Vertex> graphVertices;
    ArrayList<Edge> mst;
    Color currentColor = Color.red;

    public MinimumSpanningTree(BFSAlgorithm _parent) {
        // constructor
        super();
        parent = _parent;
        graphVertices = parent.vertices;
        graphEdges = parent.edges;
        edges = parent.edges;
        mst = parent.mst;
    }

    public MinimumSpanningTree() {
        // constructor with no parameters
    		graphEdges = new ArrayList<Edge>();
            edges = new ArrayList<Edge>();
    		graphVertices = new ArrayList<Vertex>();
    		mst = new ArrayList<Edge>();
    }


    public MinimumSpanningTree(ArrayList<Edge> graph) {
        // constructor when the only parameter is an Arraylist of edges
        graphEdges = graph;
        edges = graph;
        mst = new ArrayList<Edge>();
        graphVertices = new ArrayList<Vertex>();
    }

    public void paintComponent(Graphics g) {
        // draw the app
        super.paintComponent(g);

        for (int i = 0; i < graphVertices.size(); ++i) {

        		g.setColor(currentColor);
        		Vertex currentVertex = graphVertices.get(i);

        		if (currentVertex.hovered) {
                g.setColor(Color.yellow);
            }

            g.fillOval(currentVertex.p.x - parent.NODE_RADIUS,
                   currentVertex.p.y - parent.NODE_RADIUS,
                   2*parent.NODE_RADIUS, 2*parent.NODE_RADIUS);

        }

        for (int i = 0; i < graphEdges.size(); ++i) {

        		g.setColor(currentColor);
            Edge edge = graphEdges.get(i);

            if (edge.hovered) {
                g.setColor(Color.yellow);
            }

            Point p1 = edge.v1.p;
            Point p2 = edge.v2.p;
            g.drawLine(p1.x, p1.y, p2.x, p2.y);

            Point midpoint = edge.midPoint();
            g.drawString("Weight: " + edge.weight, midpoint.x, midpoint.y);

        }

        if (parent.mst.size() > 0) {
        for (int i = 0; i < parent.mst.size(); ++i) {

        		g.setColor(Color.blue);

            Edge edge = parent.mst.get(i);
            Vertex vt1 = edge.v1;
            Vertex vt2 = edge.v2;

            g.fillOval(vt1.p.x - parent.NODE_RADIUS, vt1.p.y - parent.NODE_RADIUS,
                    2 * parent.NODE_RADIUS, 2 * parent.NODE_RADIUS);
            g.fillOval(vt2.p.x - parent.NODE_RADIUS, vt2.p.y - parent.NODE_RADIUS,
                    2 * parent.NODE_RADIUS, 2 * parent.NODE_RADIUS);


            g.drawLine(vt1.p.x, vt1.p.y, vt2.p.x, vt2.p.y);
            Point midpoint2 = edge.midPoint();
            g.drawString("Weight: "+ edge.weight, midpoint2.x, midpoint2.y);
        }
        }

    }

    public void changeColor() {
        // change the color
        if (currentColor.equals(Color.red)) {
            currentColor = Color.yellow;
        } else {
            currentColor = Color.red;
        }
    }

    public ArrayList<Edge> getMST() {
        // returns an Arraylist of edges in the MST
    	// startCloud();
    	ArrayList<Edge> temp = new ArrayList<Edge>();
        Stack<Vertex> stackVertices = new Stack<Vertex>();
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        int size = 0;
        int[][] visitedArray = new int[1000][1000];
        		Edge e = graphEdges.get(0);
            Vertex vertex1 = e.v1;
            stackVertices.push(vertex1);
            visited.add(vertex1);
            for (int i = 0; i < graphEdges.size(); i++) {
                Edge ed = graphEdges.get(i);
                if(!vertices.contains(ed.v1)){
                    vertices.add(ed.v1);
                }

                if(!vertices.contains(ed.v2)){
                    vertices.add(ed.v2);
                }
            }
            while (!stackVertices.empty()) {
                Vertex v = stackVertices.pop();
                if(visitedArray[v.p.x][v.p.y] != 1){
                    size++;
                    System.out.println("X "+v.p.x+" Y "+v.p.y);
                    visitedArray[v.p.x][v.p.y] = 1;
                }

                for (int index = 1; index < graphEdges.size(); index++) {
                    Edge edge = graphEdges.get(index);
                    // System.out.println(edge.v1.p.x +"combine"+vertex1.p.x +"xcd"+ edge.v1.p.y +"ehjde"+vertex1.p.y);
                    // System.out.println(edge.v2.p.x +"combine"+vertex1.p.x +"xcd"+ edge.v2.p.y +"ehjde"+vertex1.p.y);
                    
                    if(edge.v1.p.x == v.p.x && edge.v1.p.y == v.p.y && visitedArray[edge.v1.p.x][edge.v1.p.y] == 1){
                        if(!visited.contains(edge.v2)){
                            stackVertices.push(edge.v2);
                            visited.add(edge.v2);
                        }
                    }
                    
                    if(edge.v2.p.x == v.p.x && edge.v2.p.y == v.p.y && visitedArray[edge.v2.p.x][edge.v2.p.y] == 1){
                        if(!visited.contains(edge.v1)){
                            visited.add(edge.v1);
                            stackVertices.push(edge.v1);
                        }
                    }
                }
            }
        //     Vertex vertex2 = e.v2;
        //     vertex1.addNeighbour(vertex2);
        //     vertex2.addNeighbour(vertex1);
        //     if(!vertices.contains(vertex1)){
        //         vertices.add(vertex1);
        //     }
        //     else if(!vertices.contains(vertex2)){
        //         vertices.add(vertex2);
        //     } 
        //     for (Vertex vertex : vertices) {
        //         if(!vertex.visited && hasCycle(vertex)){
        //             System.out.println("Has Cycle");
        //             System.out.println("x"+vertex.p.x +"y"+vertex.p.y);
        //         }
        //     }
        //     // System.out.println("No Cycle Yet");
        //     // if(!edges.contains(e)){
        //     //     edges.add(e);
        //     // }

        //     // for (int i = 0; i < graphEdges.size(); i++) {
        //     //     Edge edg = graphEdges.get(i);
        //     //     if(vertices.contains(edg.v1) || vertices.contains(edg.v2)){
        //     //         if(!edges.contains(edg)){
        //     //             edges.add(edg);
        //     //         }
        //     //     }
        //     // }
        //     Double lowest = (double) 100000;
        //     Edge edgeToAdd = null;
        //     for (int i = 0; i < edges.size(); i++) { 
        //         Edge edge = edges.get(i);
        //         if(vertices.contains(edge.v1) || vertices.contains(edge.v2)){
        //             if(edge.weight < lowest){
        //                 edgeToAdd = edges.get(i);
        //                 lowest = edge.weight;
        //             }
        //         }
        //     }
        //     // System.out.println("Size"+edges.size());

        //     // System.out.println(edges.size());
        //     if(!visited.contains(edgeToAdd) && edgeToAdd != null){
        //         visited.add(edgeToAdd);
        //     }
        //     if(!visited.contains(edgeToAdd) && edgeToAdd != null){
        //         mst.add(edgeToAdd);
        //     }
        //     // if(vertices.contains(edgeToAdd.v1) && vertices.contains(edgeToAdd.v2)){
        //     //     for (int a = 0; a < visited.size(); a++) {
        //     //         Edge edge = visited.get(a);
        //     //         for (int b = 0; b < visited.size(); b++) {
        //     //             Edge edge2 = visited.get(b);
        //     //             if(edge != edge2){
        //     //                 if(edge.v1 == edgeToAdd.v1 || edge.v1 == edgeToAdd.v2 || edge.v2 == edgeToAdd.v1 || edge.v2 == edgeToAdd.v2){
        //     //                     System.out.println("Coming till here");
        //     //                     System.out.println(!(edge2.v1 == edgeToAdd.v1 || edge2.v1 == edgeToAdd.v2 || edge2.v2 == edgeToAdd.v1 || edge2.v2 == edgeToAdd.v2));
        //     //                     if(!(edge2.v1 == edgeToAdd.v1 || edge2.v1 == edgeToAdd.v2 || edge2.v2 == edgeToAdd.v1 || edge2.v2 == edgeToAdd.v2)){
        //     //                         System.out.println("Coming");
        //     //                         if(!mst.contains(edgeToAdd) && edgeToAdd != null){
        //     //                             mst.add(edgeToAdd);
        //     //                         }
        //     //                     }
        //     //                 }
        //     //             }
        //     //         }
        //     //     }
        //     // }

        //     // if(!mst.contains(edgeToAdd) && edgeToAdd != null){
        //     //     mst.add(edgeToAdd);
        //     // }
        // }
        for (int i = 0; i+1 < visited.size(); i++) {
            mst.add(new Edge(visited.get(i), visited.get(i+1)));
        }
        return mst;
    }



    public void startCloud() {
        // create the cloud by adding the appropriate vertices
        for (int i = 0; i < graphEdges.size(); ++i) {
            graphEdges.sort(null);

            Edge edge = graphEdges.get(i);
            Vertex vertex1 = edge.v1;
            Vertex vertex2 = edge.v2;

            if (vertex1.inCloud.vertices.size() == 0) {
                vertex1.inCloud.addToCloud(vertex1);
            }
            if (vertex2.inCloud.vertices.size() == 0) {
                vertex2.inCloud.addToCloud(vertex2);
            }
    }
    }

    // public static void main(String args[]) {

    //     Vertex v1 = new Vertex(new Point());
    // 	Vertex v2 = new Vertex(new Point());
    //     Edge e1 = new Edge(v1, v2);
    //     e1.weight = 0;
    //     Edge e2 = new Edge(v1, v1);
    //     e2.weight = 2;
    //     Edge e3 = new Edge(v2, v2);
    //     e3.weight = -3;

    //     ArrayList<Edge> edges = new ArrayList<Edge>();
    //     edges.add(e1);
    //     edges.add(e2);
    //     edges.add(e3);
    //     edges.sort(null);

    //     for(Edge e : edges) {
    //     		System.out.println(e.weight);
    //     }
    // }
}
