package 校招笔试真题.富途20231011;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/10/11
 */
//80%
public class 病毒扩散 {
    static ArrayList<Integer> bd = new ArrayList<>();
    public static void main(String[] args) {
        bd.add(0);
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int k = sc.nextInt();
            int i = 1;
            if(k == 0){
                System.out.println(0);
                continue;
            }
            while (bd.get(bd.size() - 1) < k){
                bd.add(bd.get(bd.size() - 1)  + i * i);
                i++;
            }
            //如果bd.get(末尾已经>= k )
            System.out.println(bd.get(bd.size() - 2));
            bd.clear();
            bd.add(0);
        }
    }
}
