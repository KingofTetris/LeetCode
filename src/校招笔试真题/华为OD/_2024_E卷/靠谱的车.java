package 校招笔试真题.华为OD._2024_E卷;

import java.util.Arrays;
import java.util.Scanner;

public class 靠谱的车 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] arr = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();

    System.out.println(getResult(arr));
  }

  public static int getResult(int[] arr) {
    int correct = 0;

    for (int i = 0; i < arr.length; i++) {
      int fault = arr[i];
      if (fault > 4) fault--;

      for (int j = arr.length - i - 1; j > 0; j--) {
        fault *= 9;
      }

      correct += fault;
    }

    return correct;
  }
}
