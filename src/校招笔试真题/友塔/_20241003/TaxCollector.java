package 校招笔试真题.友塔._20241003;

import java.util.*;

public class TaxCollector {

    static class Road {
        int city;
        int loss;
        public Road(int city, int loss) {
            this.city = city;
            this.loss = loss;
        }
    }

    // Dijkstra's algorithm to find the minimum horse loss to each city
    public static int[] dijkstra(int n, List<List<Road>> graph, int horses) {
        int[] minLoss = new int[n];
        Arrays.fill(minLoss, Integer.MAX_VALUE);
        minLoss[0] = 0; // Start at the main city

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{0, 0}); // {city, loss}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int city = current[0];
            int loss = current[1];

            if (loss > minLoss[city]) continue;

            for (Road road : graph.get(city)) {
                int newLoss = loss + road.loss;
                if (newLoss < minLoss[road.city]) {
                    minLoss[road.city] = newLoss;
                    pq.offer(new int[]{road.city, newLoss});
                }
            }
        }
        return minLoss;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of horses, cities, and roads
        int n = sc.nextInt(); // initial number of horses
        int c = sc.nextInt(); // number of cities
        int m = sc.nextInt(); // number of roads

        // Graph representation
        List<List<Road>> graph = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            graph.add(new ArrayList<>());
        }

        // Input roads (m roads)
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // city u
            int v = sc.nextInt(); // city v
            int loss = sc.nextInt(); // horse loss on this road
            graph.get(u).add(new Road(v, loss));
            graph.get(v).add(new Road(u, loss));
        }

        // Input tax for each city except the main city (city 0)
        int[] tax = new int[c];
        for (int i = 1; i < c; i++) {
            tax[i] = sc.nextInt();
        }

        // Step 1: Calculate minimum horse loss to each city using Dijkstra
        int[] minLoss = dijkstra(c, graph, n);

        // Step 2: Determine the number of horses left after reaching each city
        List<int[]> citiesWithHorses = new ArrayList<>();
        for (int i = 1; i < c; i++) { // City 0 is the main city, so we skip it
            int remainingHorses = n - minLoss[i];
            if (remainingHorses > 0) {
                citiesWithHorses.add(new int[]{i, remainingHorses, tax[i]});
            }
        }

        // Step 3: Sort cities by tax descending, then by city number ascending
        citiesWithHorses.sort((a, b) -> {
            if (b[2] != a[2]) {
                return b[2] - a[2]; // Sort by tax (descending)
            }
            return a[0] - b[0]; // Sort by city number (ascending)
        });

        // Step 4: Collect taxes, report remaining horses, and cities visited
        int totalTax = 0;
        int remainingHorses = n;
        List<Integer> visitedCities = new ArrayList<>();

        for (int[] cityData : citiesWithHorses) {
            int city = cityData[0];
            int horsesLeft = cityData[1];
            int cityTax = cityData[2];

            totalTax += cityTax;
            remainingHorses = Math.min(remainingHorses, horsesLeft);
            visitedCities.add(city);
        }

        // Output: Maximum tax collected, remaining horses, and visited cities
        System.out.println("Maximum tax collected: " + totalTax);
        System.out.println("Remaining horses: " + remainingHorses);
        System.out.print("Visited cities: ");
        for (int city : visitedCities) {
            System.out.print(city + " ");
        }
        System.out.println();
    }
}
