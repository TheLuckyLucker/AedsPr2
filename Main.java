/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 * @author Hendrik Werner // s4549775
 * @author Constantin Blach // s4329872
 */
public class Main {
    public static void main(String[] args) {
        AdjacencyMatrix graph = new AdjacencyMatrix(System.in);
       // System.out.println(graph.toString());
        Solver solver = new Solver(graph, graph.getNrActors());
        solver.solve();
    }
}

   
