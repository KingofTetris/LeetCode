package 算法设计与分析和助教课.梅努斯算法I.第一次实验;
import java.util.*;

public class problem2 {
    /**
     *  Finds the absolute maximum value among the given numbers.
		Example#1:
		input:2 10 22 100 34 58
		output:100

		Example#2:
		input:32 46 77 1 0 2 -111 65
		output:-111
     */
    public static void main(String []args) {
    	Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(" ");
        int[] numbers = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            numbers[i] = Integer.parseInt(data[i]);
        }

        int absMax = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (Math.abs(numbers[i]) > Math.abs(absMax)) {
                absMax = numbers[i];

            }
        }
        System.out.println(absMax);
    }

}
