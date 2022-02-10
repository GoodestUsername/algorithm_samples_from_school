package AlgoLab7;

import java.util.ArrayList;
import java.util.List;

/*
 * COMP 3760, Winter (Spring) 2021, Lab 7
 *
 * Eric Dong, A01170099
 * Represents an adjacency matrix and its functions
 */
public class Graph {
    private int size;
    private int[][] matrix;
    private boolean directed = false;

    public Graph(int V) {
        matrix = new int[V][V];
        size = V;
    }

    
    /** Adds an edge to the matrix.
     * @param u coordinate.
     * @param v coordinate.
     */
    public void addEdge(int u, int v) {
        matrix[u][v] = 1;
        if (!directed) { // if its not directed, mirror the edge
            matrix[v][u] = 1;
        }
    }

    
    /** Calculates degree for a vertice.
     * @param v Vertice to get degree for.
     * @return int Number of degrees.
     */
    public int degree(int v) {
        int deg = 0;
        for (int[] row: matrix) {
            if (row[v] == 1) {
                deg++;
            }
        }
        return deg;
    }

    
    /** Returns boolean for whether or not its directed.
     * @return boolean Is directed.
     */
    public boolean isDirected() {
        return directed;
    }

    
    /** Sets the isDirected parameter of the graph.
     * @param isDirected boolean to set for whether or not its directed
     */
    public void setDirected(boolean isDirected) {
        directed = isDirected;
    }
    
    
    /** Calculates the in Degree of a vertice.
     * @param v Vertices.
     * @return int Number of in degrees.
     */
    public int inDegree(int v) {
        if (!directed) {
            return -1;
        }

        int deg = 0;
        for (int i = 0; i != size; i++) {
            if (matrix[i][v] == 1) {
                deg++;
            }
        }
        return deg;
    }

    
    /** Calculates the number of out degrees.
     * @param v Vertices.
     * @return int Number of out degrees.
     */
    public int outDegree(int v) {
        if (!directed) {
            return -1;
        }

        int deg = 0;
        for (int i = 0; i != size; i++) {
            if (matrix[v][i] == 1) {
                deg++;
            }
        }
        return deg;
    }


    public void DFS() {
        boolean[] visited = new boolean[size];
        for (int v = 0; v < size; v++) {
            if (!visited[v]) {
                dfs(v, visited);
            }
        }
    }

    
    /** Traverses matrix using dfs method.
     * @param v integer locating the vertice.
     * @param visited boolean map storing whether or not the each vertice is visted.
     */
    private void dfs(int v, boolean[] visited) {
        if (!visited[v]) {
            visited[v] = true;
            System.out.println("Visiting vertex " + v);

            for (int i = 0; i < size; i++) {
                if (matrix[v][i] == 1 && !visited[i]) {
                    dfs(i, visited);
                }
            }
        }

    }

    public void BFS() {
        boolean[] visited = new boolean[size];
        for (int v = 0; v < size; v++) {
            if (!visited[v]) {
                bfs(v, visited);
            }
        }
    }

    
    /** Traverses matrix using bfs method.
     * @param v integer locating the vertice.
     * @param visited boolean map storing whether or not the each vertice is visted.
     */
    private void bfs(int v, boolean[] visited) {
        if (!visited[v]) {
            visited[v] = true;
            List<Integer> q = new ArrayList<>();
            q.add(v);
            while(!q.isEmpty()) {
                int vis = q.get(0);
                System.out.println("Visiting vertex " + vis);
                q.remove(q.get(0));

                for (int w = 0; w < size; w++) {
                    if (matrix[vis][w] == 1 && !visited[w]) {
                        q.add(w);
                        visited[w] = true;
                    }
                }
            }
        }
    }

    
    /** Returns string representation of the matrix.
     * @return String matrix represented as a string.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row: matrix) {
            for (int item: row) {
                sb.append(item);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
