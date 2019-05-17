package graphs;

import java.io.*;
import java.util.*;

public class SPA implements Runnable {

    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    Scanner in = new Scanner(new InputStreamReader(System.in));
    class Edge implements Comparable<Edge> {

        int u;
        long cost;
        public Edge(int u, long cost) {
            this.u = u;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge arg0) {
            return Long.signum(cost - arg0.cost);
        }
    }

    private void solution() throws IOException {
        System.out.println("Please enter number of nodes:");
        int n = in.nextInt();
        System.out.println("Please enter number of edges:");
        int m = in.nextInt();
        System.out.println("Please enter the node you want to find the shortest path from:");
        int source = in.nextInt()-1;
        List<Edge>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<Edge>();
        }
        System.out.println("Enter the start and end of edge and the weight of the edge:");
        for (int i = 0; i < m; ++i) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int cost = in.nextInt();
            adj[x].add(new Edge(y, cost));
            adj[y].add(new Edge(x, cost));
        }
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();
        dist[source] = 0;
        q.add(new Edge(source, 0));
        while (!q.isEmpty()) {
            Edge cur = q.poll();
            if (dist[cur.u] < cur.cost) continue;
            for (Edge e : adj[cur.u]) {
                Edge next = new Edge(e.u, e.cost + cur.cost);
                if (next.cost < dist[next.u]) {
                    dist[next.u] = next.cost;
                    q.add(next);
                }
            }
        }
        System.out.println("The shortest path between the nodes and node "+(source+1)+" are:");
        for(int i=0;i<n;i++){
            if(dist[i]==Long.MAX_VALUE)System.out.println("No Path to the node "+(i+1));
            else System.out.println("The shortest path to node "+(i+1)+" is "+dist[i]);
        }
    }

    @Override
    public void run() {
        try {
            solution();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void start() throws IOException {
      new Thread(null, new SPA(), "", 1 << 28).start();
    }
}

