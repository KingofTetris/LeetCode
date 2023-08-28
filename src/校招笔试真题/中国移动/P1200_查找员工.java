package 校招笔试真题.中国移动;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/5/22
 */

public class P1200_查找员工 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.next();
        String[] s = s1.split(" ");
        int flag = 0;
        for (String s3 : s) {
            if (s2.equals(s3)){
                flag = 1;
                int start = s1.indexOf(s3);
                System.out.println("Found at index : " + start);
            }
        }
        if(flag == 0){
            System.out.println("Not found");
        }
    }
}
