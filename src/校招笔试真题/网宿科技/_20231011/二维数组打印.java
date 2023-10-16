package 校招笔试真题.网宿科技._20231011;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/10/11
 */
public class 二维数组打印 {
    public static void main(String[] args) {
       ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        row1.add(3);
        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(4);
        row2.add(5);
        row2.add(6);
        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(7);
        row3.add(8);
        row3.add(9);
        ArrayList<Integer> row4 = new ArrayList<>();
        row4.add(10);
        row4.add(11);
        row4.add(12);
        arr.add(row1);
        arr.add(row2);
        arr.add(row3);
        arr.add(row4);

        ArrayList<Integer> println = println(arr, 4, 3);
        System.out.println(println);
    }


    public static ArrayList<Integer> println (ArrayList<ArrayList<Integer>> arr, int n, int m) {
        // write code here
        ArrayList<Integer> res = new ArrayList<>();
        int total = n * m;
        int num = 0;
        int row = 0;
        int col = 0;
        while (num < total){
            while (col < m){
                res.add(arr.get(row).get(col));
                col++;
                num++;
            }
            if (num == total){
                break;
            }
            //col == m
            col--;// m - 1
            row++;
            while (col >= 0){
                res.add(arr.get(row).get(col));
                col--;
                num++;
            }
            //col == -1
            col++;// 0
            row++;
            if (num == total){
                break;
            }
        }
        return res;
    }

}
