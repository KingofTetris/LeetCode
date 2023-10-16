
/*Lab 2: Problem 2 - Sorting combined two sorted arrays
 * The input is two sorted integer arrays (as input strings from console),
 *  which are sorted from small to large.
Read those input strings as int arrays and combine those arrays being one,
* and sorting it from small to large.
Print out the combined and sorted one using the output format as in the following examples.
Example#1

Input :1,3,5,9,27
2,10,12,15
Output:
1,2,3,5,9,10,12,15,27

Example#2
Input :2,4,8,10,12
1,2,3,4,5
Output:
1,2,2,3,4,4,5,8,10,12
 */

package 算法设计与分析和助教课.梅努斯算法I.第二次实验;

import java.util.Scanner;

public class Problem02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String array1 = scanner.nextLine();
		String array2 = scanner.nextLine();
		String array = array1 + "," + array2;
		String[] arr = array.split(",");
		int[] a = new int[arr.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(arr[i]);
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (i != 0)
				System.out.print(",");
			System.out.print(a[i]);

		}
	}

}
