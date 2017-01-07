/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.Arrays;

/**
 * @author Hendrik Werner // s4549775
 * @author Constantin Blach // s4329872
 */
public class Solver {

    private AdjacencyMatrix graph;
    private int totalNumber;
    private boolean done;
    private boolean winner;     // true if girl, false if boy

    public Solver(AdjacencyMatrix g, int n) {
        this.graph = g;
        this.totalNumber = n;
        done = false;
    }

    public void solve() {
        System.out.println("solving");
        int girlChose, boyChose;
        girlChose = makeFirstMove();
        while (!done) {
            boyChose = makeBoyMove(girlChose);

            if (boyChose == Integer.MAX_VALUE) {          // boy loses
                winner = true;
                break;
            }

            graph.wipeActress(girlChose);

            girlChose = makeGirlMove(boyChose);
            if (girlChose == Integer.MAX_VALUE) {         // girl loses
                winner = false;
                break;
            }
            graph.wipeActor(boyChose);
        }
        if (winner) {
            System.out.println("girl wins");
        } else {
            System.out.println("boy wins");
        }

    }

    private int makeFirstMove() {
        int choice = Integer.MAX_VALUE;
        for (int i = 0; i < totalNumber; i++) {
            if (graph.getAdjacentActors(i).length == 0) {     // free win for girl
                return i;
            } else {
                int sizeToTest = graph.getAdjacentActors(i).length;
                int[] arrayToTest = graph.getAdjacentActors(i);
                for (int j = 0; j < sizeToTest; j++) {
                    if (graph.getAdjacentActresses(arrayToTest[j]).length == 0) {         // Dead end, this actress results in a loss 
                        break;
                    }
                }
                choice = i;
            }
        }
        return choice;
    }

    private int makeGirlMove(int chosen) {
        int choice = Integer.MAX_VALUE;
        if (graph.getAdjacentActresses(chosen).length == 0) {     // give up
            return choice;
        }
        for (int i : graph.getAdjacentActresses(chosen)) {
            if (graph.getAdjacentActors(i).length == 0) {     // free win for girl
                System.out.println("girl free win");
                done = true;
                return i;
            } else if (graph.getAdjacentActors(i).length == 1) {    // 1 matching actor, this actor has no dead end
                if (graph.getAdjacentActresses(graph.getAdjacentActors(i)[0]).length > 0) {
                    choice = i;
                }
            } else if (graph.getAdjacentActors(i).length > 1) {
                choice = i;
            }
        }
        return choice;
    }

    private int makeBoyMove(int chosen) {
        int choice = Integer.MAX_VALUE;
        if (graph.getAdjacentActors(chosen).length == 0) {                 // give up
            return choice;
        }
        for (int i : graph.getAdjacentActors(chosen)) {
            if (graph.getAdjacentActresses(i).length == 1) {
                // free win for boy
                System.out.println("boy free win");
                done = true;
                winner = false;
                return i;
            } else if (graph.getAdjacentActresses(i).length == 1) {
                if (graph.getAdjacentActors(graph.getAdjacentActresses(i)[0]).length > 0) {
                    choice = i;
                }
            } else if (graph.getAdjacentActresses(i).length > 1) {
                choice = i;
            }
        }
        return choice;
    }

}
