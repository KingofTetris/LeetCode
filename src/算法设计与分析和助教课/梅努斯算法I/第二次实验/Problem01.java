
/*
 * Lab 2: Problem 1 - Find the closest pair
 * Given an input string of a sequence of integers from the console (e.g., using Scanner),
 * read the string and parse it as an unsorted array of integers, then find and print out
 * the closest pair of the adjacent elements of this array using the output pattern in the following examples.
 * Example 1
 * input=9,15,17,5,100,16
 * output=Pair found at index 1 and 2
 * Example 2
 * input=9,15,17,5,100,16,18
 * output=Pair found at index 1 and 2
 * Pair found at index 5 and 6
 */
package 算法设计与分析和助教课.梅努斯算法I.第二次实验;

import java.util.Scanner;

public class Problem01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String in = scan.nextLine();
		String[] arr_str = in.split(",");
		compare(arr_str);
	}

	public static void compare(String[] in) {
		int k = Math.abs(Integer.parseInt(in[1]) - Integer.parseInt(in[0]));
		for (int i = 0; i < in.length - 1; i++) {
			if (k > Math.abs(Integer.parseInt(in[i]) - Integer.parseInt(in[i + 1]))) {
				k = Math.abs(Integer.parseInt(in[i]) - Integer.parseInt(in[i + 1]));
			}
		}
		for (int i = 0; i < in.length - 1; i++) {
			if (k == Math.abs(Integer.parseInt(in[i]) - Integer.parseInt(in[i + 1]))) {
				System.out.println("Pair found at index " + i + " and " + (i + 1));
			}
		}
	}

}
