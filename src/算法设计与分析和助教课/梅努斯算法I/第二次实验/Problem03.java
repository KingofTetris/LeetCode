/*
 * Lab 2: Problem 3 - Remove duplicate elements from the array
Given a string array, the task is to remove the duplicate elements from the array.

Example 1:
input=a,b,c,c,d,a,b,c,d,e
output=a b c d e

Example 2:
input=1,2,3,4,5,1,2,3
output=1 2 3 4 5
 */
package 算法设计与分析和助教课.梅努斯算法I.第二次实验;

import java.util.Scanner;

public class Problem03 {

	/*
	 * this method find out duplicated items and set it with the value "x"
	 */

	public static int updateduplicates(String a[], int n) {
		if (n == 0 || n == 1) {
			return n;
		}

		int j = n;

		for (int i = n - 1; i > 0; i--) {
			for (int k = i - 1; k >= 0; k--) {
				if (a[i].equals(a[k])) {
					a[i] = "x";
					j--;
				}

			}
		}
		return j;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String in = scan.nextLine();
		String[] a = in.split(",");
		int n = a.length;
		// replace the value of duplicate item with "x"
		n = updateduplicates(a, n);

		// create a new deduplicated array
		String[] dedup = new String[n];
		// only copy items which are not "x"
		for (int i = 0; i < a.length; i++) {
			if (!(a[i].equals("x"))) {
				dedup[n - 1] = a[i];
				System.out.print(dedup[n - 1] + " ");
				n--;

			}

		}
	}
}
