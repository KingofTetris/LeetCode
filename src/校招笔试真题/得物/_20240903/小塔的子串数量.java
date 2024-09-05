package 校招笔试真题.得物._20240903;

import java.util.*;

public class 小塔的子串数量 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.next();
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i <= n - k; ) {
            String substring = s.substring(i, i + k);
            if (isGood(substring)) {
                //是好串
                map.put(substring, map.getOrDefault(substring, 0) + 1);
                //不重叠，直接让i = i + k 而不是i++
                i += k;
            }
            //不是好串i++;
            else{
                i++;
            }
        }

//        System.out.println(map);

        int res = 0;
        for (Integer value : map.values()) {
            res = Math.max(value,res);
        }
        System.out.println(res);
    }

    public static boolean isGood(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
