/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Graphs {
    public boolean decision;
    public boolean direction;
    public static void main(String[] arg) throws IOException
    {
        Graphs graph = new Graphs();
        Choice choice = new Choice(graph);
        while(choice.visible)
        {
            System.out.println("GUI still visible");
        }
        for(int i=0; i<1000;i++)
        System.out.println("");
        if(graph.decision)
        {
            System.out.println("Dijkstra's Algorithm");
            MST.run();
        }
        else
        {
            System.out.println("Shortest Path Algorithm");
            SPA.start();
        }
    }
}
