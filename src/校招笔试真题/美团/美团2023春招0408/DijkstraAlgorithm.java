package 校招笔试真题.美团.美团2023春招0408;

/**
 * @author by KingOfTetris
 * @date 2023/4/11
 */
import java.util.Arrays;

public class DijkstraAlgorithm {
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static int[][] graph;
    private static boolean[] visited;
    private static int[] distance;

    private static int n; // number of vertices
    public static void main(String[] args) {
        n = 5; // example number of vertices
        graph = new int[][]{
                {0, 10, 3, 20, MAX_VALUE},
                {MAX_VALUE, 0, MAX_VALUE, 5, MAX_VALUE},
                {MAX_VALUE, 2, 0, MAX_VALUE, 15},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, 0, 11},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 0}
        };
        visited = new boolean[n];
        distance = new int[n];
        Arrays.fill(distance, MAX_VALUE);
        distance[0] = 0; // set distance to starting vertex to 0
        dijkstra(0);
        System.out.println(Arrays.toString(distance));
    }

    private static void dijkstra(int source) {
        for (int i = 0; i < n - 1; i++) {
            int minVertex = getMinimumVertex();
            visited[minVertex] = true;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && graph[minVertex][j] != MAX_VALUE) {
                    int newDistance = distance[minVertex] + graph[minVertex][j];
                    if (newDistance < distance[j]) {
                        distance[j] = newDistance;
                    }
                }
            }
        }
    }

    private static int getMinimumVertex() {
        int minVertex = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }
}
