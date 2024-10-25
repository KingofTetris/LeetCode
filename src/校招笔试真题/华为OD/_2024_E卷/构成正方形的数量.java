package 校招笔试真题.华为OD._2024_E卷;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class 构成正方形的数量 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[] coordinates = new String[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = sc.nextLine();
        }

        System.out.println(getResult(n, coordinates));
    }

    public static int getResult(int n, String[] coordinates) {
        int squareCount = 0;

        HashSet<String> set = new HashSet<>(Arrays.asList(coordinates));

        for (int i = 0; i < n; i++) {
            int[] arr1 = Arrays.stream(coordinates[i].split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = arr1[0], y1 = arr1[1];

            for (int j = i + 1; j < n; j++) {
                int[] arr2 = Arrays.stream(coordinates[j].split(" ")).mapToInt(Integer::parseInt).toArray();
                int x2 = arr2[0], y2 = arr2[1];

                int x3 = x1 - (y1 - y2), y3 = y1 + (x1 - x2);
                int x4 = x2 - (y1 - y2), y4 = y2 + (x1 - x2);
                if (set.contains(x3 + " " + y3) && set.contains(x4 + " " + y4)) squareCount++;

                int x5 = x1 + (y1 - y2), y5 = y1 - (x1 - x2);
                int x6 = x2 + (y1 - y2), y6 = y2 - (x1 - x2);
                if (set.contains(x5 + " " + y5) && set.contains(x6 + " " + y6)) squareCount++;
            }
        }

        return squareCount / 4;
    }
}
