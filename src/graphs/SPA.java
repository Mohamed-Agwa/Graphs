package graphs;

import java.io.*;
import java.util.*;

public class SPA{

    static Scanner in = new Scanner(new InputStreamReader(System.in));
    static final int INF = Integer.MAX_VALUE; 
    class AdjListNode 
    { 
        private int v; 
        private int weight; 
        AdjListNode(int _v, int _w) { v = _v;  weight = _w; } 
        int getV() { return v; } 
        int getWeight()  { return weight; } 
    } 
  
    // Class to represent graph as an adjcency list of 
    // nodes of type AdjListNode 
    class Graph 
    { 
        private int V; 
        private LinkedList<AdjListNode>adj[]; 
        Graph(int v) 
        { 
            V=v; 
            adj = new LinkedList[V]; 
            for (int i=0; i<v; ++i) 
                adj[i] = new LinkedList<AdjListNode>(); 
        } 
        void addEdge(int u, int v, int weight) 
        { 
            AdjListNode node = new AdjListNode(v,weight); 
            adj[u].add(node);// Add v to u's list 
        } 
  
        // A recursive function used by shortestPath. 
        // See below link for details 
        void topologicalSortUtil(int v, Boolean visited[], Stack stack) 
        { 
            // Mark the current node as visited. 
            visited[v] = true; 
            Integer i; 
  
            // Recur for all the vertices adjacent to this vertex 
            Iterator<AdjListNode> it = adj[v].iterator(); 
            while (it.hasNext()) 
            { 
                AdjListNode node =it.next(); 
                if (!visited[node.getV()]) 
                    topologicalSortUtil(node.getV(), visited, stack); 
            }  
            stack.push(new Integer(v)); 
        } 
  
        void shortestPath(int s) 
        { 
            Stack stack = new Stack(); 
            int dist[] = new int[V]; 

            Boolean visited[] = new Boolean[V]; 
            for (int i = 0; i < V; i++) 
                visited[i] = false; 
            for (int i = 0; i < V; i++) 
                if (visited[i] == false) 
                    topologicalSortUtil(i, visited, stack); 

            for (int i = 0; i < V; i++) 
                dist[i] = INF; 
            dist[s] = 0; 
 
            while (stack.empty() == false) 
            {  
                int u = (int)stack.pop();  
                Iterator<AdjListNode> it; 
                if (dist[u] != INF) 
                { 
                    it = adj[u].iterator(); 
                    while (it.hasNext()) 
                    { 
                        AdjListNode i= it.next(); 
                        if (dist[i.getV()] > dist[u] + i.getWeight()) 
                            dist[i.getV()] = dist[u] + i.getWeight(); 
                    } 
                } 
            } 
   
            for (int i = 0; i < V; i++) 
            { 
                if (dist[i] == INF) 
                    System.out.print( "INF "); 
                else
                    System.out.print( dist[i] + " "); 
            } 
        } 
    } 
  
    Graph newGraph(int number) 
    { 
        return new Graph(number); 
    } 
  
    public static void run() 
    { 
        SPA t = new SPA(); 
        boolean negative = false;
        System.out.println("Please enter # of nodes");
        int n = in.nextInt();                
        Graph g = t.newGraph(n);
        System.out.println("Please enter # of edges");
        int ed = in.nextInt();
        
        
        for(int i=0 ; i < ed ; i++)
        {
            System.out.println("For edge " + (i+1) + " Enter start node");
            int start = in.nextInt();
            System.out.println("For edge " + (i+1) + " Enter end node");
            int end = in.nextInt();
            System.out.println("For edge " + (i+1) + " Enter weight");
            int weight = in.nextInt();
            if(weight <0)
                negative = true;
            while(negative)
            {
                System.out.println("Negative weight isn't allowed. Try again.");
                System.out.println("For edge " + (i+1) + "Enter weight");
                weight = in.nextInt();
                if(weight>0)
                    negative=false;
            }
            g.addEdge(start, end, weight);
        }
        
        System.out.println("There are " + n + " nodes.");
        System.out.println("Please enter the source as a number between 1 and " + n);        
        int s = in.nextInt(); 
        System.out.println("Following are shortest distances "+ 
                            "from source " + s ); 
        g.shortestPath(s-1); 
    } 
}

