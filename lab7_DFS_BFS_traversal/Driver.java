package AlgoLab7;

/*
 * COMP 3760, Winter (Spring) 2021, Lab 7
 *
 * Eric Dong, A01170099
 * Tests and performs various methods of graph.
 */

public class Driver {
    
    /** Drives the program.
     * @param args not used.
     */
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(1, 0);
        graph.addEdge(3, 0);
        graph.addEdge(4, 0);
        graph.addEdge(2, 1);
        graph.addEdge(3, 2);
        graph.addEdge(4, 3);
        graph.addEdge(1, 4);
        System.out.println(graph);
        System.out.println("Degree of vertex 1: " + graph.degree(1));
        System.out.println("is directed: " + graph.isDirected());

        System.out.println("___________________");
        Graph graph_2 = new Graph(4);
        graph_2.addEdge(1, 0);
        graph_2.addEdge(2, 1);
        graph_2.addEdge(3, 2);
        System.out.println(graph_2);
        System.out.println("Degree of vertex 2: " + graph_2.degree(2));

        System.out.println("___________________");
        Graph graph_3 = new Graph(6);
        graph_3.addEdge(0, 2);
        graph_3.addEdge(0, 4);
        graph_3.addEdge(1, 3);
        graph_3.addEdge(1, 5);
        graph_3.addEdge(2, 4);
        graph_3.addEdge(3, 5);
        System.out.println(graph_3);
        System.out.println("Degree of vertex 3: " + graph_3.degree(3));

        System.out.println("___________________");
        Graph graph_d = new Graph(5);
        graph_d.setDirected(true);
        graph_d.addEdge(0, 2);
        graph_d.addEdge(2, 2);
        graph_d.addEdge(2, 3);
        graph_d.addEdge(2, 4);
        graph_d.addEdge(3, 0);
        graph_d.addEdge(4, 3);
        System.out.println(graph_d);
        System.out.println("is directed: " + graph_d.isDirected());
        System.out.println("Degree of vertex 3 in: " + graph_d.inDegree(3));
        System.out.println("Degree of vertex 3 out: " + graph_d.outDegree(3));

        System.out.println("___________________");
        Graph graph_dfs = new Graph(8);
        graph_dfs.addEdge(0, 1);
        graph_dfs.addEdge(0, 2);
        graph_dfs.addEdge(0, 4);
        graph_dfs.addEdge(1, 3);
        graph_dfs.addEdge(1, 5);
        graph_dfs.addEdge(2, 3);
        graph_dfs.addEdge(2, 6);
        graph_dfs.addEdge(3, 7);
        graph_dfs.addEdge(4, 5);
        graph_dfs.addEdge(4, 6);
        graph_dfs.addEdge(5, 7);
        graph_dfs.addEdge(6, 7);
        System.out.println(graph_dfs);
        System.out.println("DFS:");
        graph_dfs.DFS();
        System.out.println("BFS:");
        graph_dfs.BFS();
    }
}
