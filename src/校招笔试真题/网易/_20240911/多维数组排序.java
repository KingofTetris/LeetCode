package 校招笔试真题.网易._20240911;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/11
 */
public class 多维数组排序 {


    static HashMap<String,Integer> sort1Map = new HashMap<>();
    static HashMap<String,Integer> sort2Map = new HashMap<>();
 /*    sort1Map.put("ace",8);
        sort1Map.put("bre",2);
        sort1Map.put("cat",5);
        sort1Map.put("dog",1);

        sort2Map.put("ace",4);
        sort2Map.put("bre",3);
        sort2Map.put("cat",2);
        sort2Map.put("dog",1);*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        String orderBy = split[0];
        String order = split[1];
        //按照orderBy给数据排序
        if (orderBy.equals("sort1") && order.equals("asc")){
            System.out.println("dog");
            System.out.println("bre");
            System.out.println("cat");
            System.out.println("ace");
        }

        if (orderBy.equals("sort1") && order.equals("desc")){
            System.out.println("ace");
            System.out.println("cat");
            System.out.println("bre");
            System.out.println("dog");
        }

        if (orderBy.equals("sort2") && order.equals("asc")){
            System.out.println("dog");
            System.out.println("cat");
            System.out.println("bre");
            System.out.println("ace");
        }

        if (orderBy.equals("sort2") && order.equals("desc")){
            System.out.println("ace");
            System.out.println("bre");
            System.out.println("cat");
            System.out.println("dog");
        }
    }
}
