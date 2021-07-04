import java.util.ArrayList;

public class GlobalNet
{
    //creates a global network
    //O : the original graph
    //regions: the regional graphs
    private static int[] dist;
    public static Graph run(Graph O, Graph[] regions)
    {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        Graph gn = new Graph(O.V());
        int[] prev;
        gn.setCodes(O.getCodes());

        //add all individual region edges
        for (int i = 0; i < regions.length; i++) {
            Graph add = regions[i];
            edges.addAll(add.edges());
        }

        for (int i = 0; i < regions.length - 1; i++) { //added - 1
            prev = dijkstras(O, regions[i]);

            for (int j = i + 1; j < regions.length; j++) {
                Graph b = regions[j];
                String[] vertices = b.getCodes();
                int min_index = -1;
                int min_dist = Integer.MAX_VALUE;

                for (int k = 0; k < vertices.length; k++) {
                    String vertex = vertices[k];
                    int index = O.index(vertex);

                    if (dist[index] < min_dist) { //find index of min dist
                        min_dist = dist[index];
                        min_index = index;
                    }
                }

                edges = merge(regions[i], b, O, edges, prev, min_index);
            }
        }

        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i) != null) {
                System.out.println(edges.get(i).toString());
            }
        }
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            gn.addEdge(e);
        }
        return gn;
    }

    public static ArrayList<Edge> merge(Graph a, Graph b, Graph O, ArrayList<Edge> edges, int[] prev, int min_index) {
        int prev_vertex = prev[min_index];
        boolean cont = true;
        while (cont) {
            edges.add(O.getEdge(min_index, prev_vertex));
            String code = O.getCode(prev_vertex);
            if (a.index(code) != -1) {
                cont = false;
            } else {
                min_index = prev_vertex;
                prev_vertex = prev[prev_vertex];
            }
        }
        return edges;
    }

    public static int[] dijkstras(Graph O, Graph a) {
        String[] vertices = O.getCodes();
        int num_vertices = O.V();
        dist = new int[O.V()];
        int[] prev = new int[O.V()];
        DistQueue q = new DistQueue(num_vertices);

        for (int v = 0; v < vertices.length; v++) {
            int source = O.index(vertices[v]);
            if (a.index(vertices[v]) < 0) {
                dist[source] = Integer.MAX_VALUE;
            }
            else { //source vertex
                dist[source] = 0;
            }
            prev[source] = - 1;
            q.insert(source, dist[source]);
        }

        while(!q.isEmpty()) {
            int u = q.delMin();
            ArrayList<Integer> adj_vertices = O.adj(u);
            for (int v = 0; v < adj_vertices.size(); v++) {
                int edge = adj_vertices.get(v);
                int new_dist = dist[u] + O.getEdgeWeight(u, edge);
                if (new_dist < dist[edge]) {
                    dist[edge] = new_dist;
                    prev[edge] = u;
                    q.set(edge, new_dist);
                }
            }
        }
        for (int k = 0; k < prev.length; k++) {
            System.out.print(k + " ");
        }
        System.out.println();

        for (int k = 0; k < prev.length; k++) {
            System.out.print(prev[k] + " ");
        }
        System.out.println();

        for (int k = 0; k <dist.length; k++) {
            System.out.print(dist[k] + " ");
        }
        System.out.println();
        System.out.println();
        return prev;
    }
}



