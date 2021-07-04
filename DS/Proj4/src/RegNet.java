import java.util.ArrayList;

public class RegNet
{
    //creates a regional network
    //G: the original graph
    //max: the budget
    public static Graph run(Graph G, int max)
    {
        ArrayList<Edge> list = new ArrayList<Edge>();
        list = G.sortedEdges();

        Graph mst = new Graph(G.V());
        mst.setCodes(G.getCodes());
        UnionFind set = new UnionFind(G.V());

        while (mst.E() < G.V() - 1 && list.size() > 0) {
            Edge e = list.remove(0);
            if (e != null && !set.connected(G.index(e.u), G.index(e.v))) {
                mst.addEdge(e);
                set.union(G.index(e.u), G.index(e.v));
            }
        }

        ArrayList<Edge> mst_list = new ArrayList<Edge>();
        while (mst.totalWeight() > max) {
            mst_list = mst.sortedEdges();
            for (int i = mst_list.size() - 1; i > -1; i--) {
                Edge e = mst_list.get(i);
                if (mst.deg(e.u) == 1 || mst.deg(e.v) == 1) {
                    mst.removeEdge(e);
                    i = -1;
                }
            }
        }

        mst = mst.connGraph();
        /*String[] verices = mst.getCodes();
        ArrayList<String> qq = new ArrayList<>();
        for (int i = 0; i < verices.length; i++) {
            ArrayList<Integer> adj_v = mst.adj(verices[i]);
            for (int j = 0; j < adj_v.size(); j++) {
                System.out.println(verices[i] + ": " + mst.adj(verices[i]));
                //System.out.println(vertices[i] + ": " + adj_v.get(j));
            }
        }*/

        //step 2
        String[] vertices = mst.getCodes();
        ArrayList<Edge> stops = new ArrayList<Edge>();
        ArrayList<String> q = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            String first_vertex = vertices[i];
            ArrayList<Integer> adj_v = mst.adj(first_vertex);

            for (int j = 0; j < adj_v.size(); j++) {
                q.add(mst.getCode(adj_v.get(j)));
            }
            stops = bfs(G, mst, q, first_vertex, stops);
        }

        for (int i = 0; i < stops.size(); i++) {
            if (mst.totalWeight() + stops.get(i).w <= max) {
                mst.addEdge(stops.get(i));
            }
        }

        System.out.println(mst.totalWeight() + " " + max);
        return mst;
    }

    public static ArrayList<Edge> bfs(Graph G, Graph mst, ArrayList<String> q,
                                      String first_vertex, ArrayList<Edge> stops) {
        int stop_num = 0;
        ArrayList<String> visited_vertices = new ArrayList<String>();
        visited_vertices.add((first_vertex));
        while(!q.isEmpty()) {
            String second_vertex = "";
            int size = q.size();
            for (int s = 0; s < size; s++) {
                second_vertex = q.remove(0);
               // System.out.println(second_vertex);
                visited_vertices.add((second_vertex));

                int w = G.getEdgeWeight(G.index(first_vertex), G.index(second_vertex)); //create edge of
                Edge e = new Edge(first_vertex, second_vertex, w); //first vertex and its adj vertice
                e.setStops(stop_num); //set stop number for the edge

                if(!stops.contains(e) && e.getStops() != 0) {
                    //stops.add(e);
                    stops = sort(stops, e);
                }

                //finding adjacent vertices FOR the adjacent vertice
                ArrayList<Integer> adj_vertices = mst.adj(second_vertex);
                for (int v = 0; v < adj_vertices.size(); v++) {
                    String third_vertex = (mst.getCode(adj_vertices.get(v)));

                    if (third_vertex!= (first_vertex) && !visited_vertices.contains(third_vertex)) {
                        q.add(mst.getCode(adj_vertices.get(v)));
                    }
                }
            }
            stop_num++;
        }
        return stops;
    }

    //sort in descending order of stops, ascending order of weights
    public static ArrayList<Edge> sort(ArrayList<Edge> arraylist, Edge e) {
        arraylist.add(0, e);

        for (int i = 0; i < arraylist.size(); i++) {
            for (int j = arraylist.size() - 1; j > i; j--) {
                if (arraylist.get(i).getStops() < arraylist.get(j).getStops()) {
                    Edge tmp = arraylist.get(i);
                    arraylist.set(i,arraylist.get(j));
                    arraylist.set(j,tmp);
                }

            }

        }

        for (int i = 0; i < arraylist.size() - 1; i++) {
                if (arraylist.get(i).getStops() == arraylist.get(i + 1).getStops() && arraylist.get(i).w > arraylist.get(i + 1).w) {
                    Edge tmp = arraylist.get(i);
                    arraylist.set(i,arraylist.get(i + 1));
                    arraylist.set(i + 1,tmp);
                }
        }
        return arraylist;
    }
}
