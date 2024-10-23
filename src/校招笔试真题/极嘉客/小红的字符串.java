package 校招笔试真题.极嘉客;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/18
 */
import java.util.Scanner;
//小红拿到了一个只包含r e d三个字母的字符串，她想知道这个字符串有多少个子串满足
//r e d三种字母的数量严格相等。
//注意空串不算子串
//例如redrde
//包含 [red]rde,r[edr]de,red[rde],[redrde]四种情况
//所以返回4

public class 小红的字符串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int count = getSubStringCount(s);
        System.out.println(count);
    }

    public static int getSubStringCount(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Character,Integer> map = new HashMap<>(); //r e d 的数量
            map.put(s.charAt(i),1);
            for (int j = i + 1; j < n; j++) { // 枚举所有可能的子串
                char c = s.charAt(j);
                map.put(c,map.getOrDefault(c,0) + 1);
                if(cal(map)){
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean cal(HashMap<Character, Integer> map) {
        int r = map.getOrDefault('r',0);
        int e = map.getOrDefault('e',0);
        int d = map.getOrDefault('d',0);
        return r == e && e == d;
    }
}
