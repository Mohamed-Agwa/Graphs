package graphs;

import java.io.*; 
import java.util.*; 
  
class MST
{ 

    private int V=5; 
    static Scanner in = new Scanner(new InputStreamReader(System.in));
    
    int minKey(int key[], Boolean mstSet[]) 
    { 
      
        int min = Integer.MAX_VALUE, min_index=-1; 
  
        for (int v = 0; v < V; v++) 
            if (mstSet[v] == false && key[v] < min) 
            { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    void printMST(int parent[], int n, int graph[][]) 
    { 
        System.out.println("Edge \tWeight"); 
        for (int i = 1; i < V; i++) 
            System.out.println(parent[i]+" - "+ i+"\t"+ 
                            graph[i][parent[i]]); 
    } 
  

    void primMST(int graph[][]) 
    { 

        int parent[] = new int[V]; 
        int key[] = new int [V]; 
        Boolean mstSet[] = new Boolean[V]; 
        for (int i = 0; i < V; i++) 
        { 
            key[i] = Integer.MAX_VALUE; 
            mstSet[i] = false; 
        } 
        key[0] = 0;   
        parent[0] = -1;
        for (int count = 0; count < V-1; count++) 
        { 
            int u = minKey(key, mstSet);  
            mstSet[u] = true; 
            for (int v = 0; v < V; v++) 
  
                if (graph[u][v]!=0 && mstSet[v] == false && 
                    graph[u][v] < key[v]) 
                { 
                    parent[v] = u; 
                    key[v] = graph[u][v]; 
                } 
        } 
  
        printMST(parent, V, graph); 
    } 
  
    public static void run() 
    { 
        MST t = new MST(); 
        System.out.println("Please enter number of vertices");        
        t.V = in.nextInt();
        int graph[][] = new int[t.V][t.V];
        System.out.println("Please enter the adjacency matrix:");
        for(int i=0;i<t.V;i++)
        {
            for(int j = 0; j<t.V;j++)
            {
                System.out.println("Please enter: [" + i + "][" +j + "]" );
                graph[i][j] = in.nextInt();
            }
        }
        t.primMST(graph); 
    } 
} 

