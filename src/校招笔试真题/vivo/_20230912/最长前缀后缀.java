package 校招笔试真题.vivo._20230912;

/**
 * @author by KingOfTetris
 * @date 2023/9/13
 */


//给你一个字符串，找出他的最长前缀后缀，两字符串不能重叠
//比如 aaaa 最长前后缀是aa 返回2
//abcdssabcd 最长前后缀是abcd 返回4
//abcdsabcd 最长前后缀是abcd 返回4
public class 最长前缀后缀 {
    public static void main(String[] args) {
        String s = "aaasdaaa";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        //分奇偶,从后往前对比即可
        int res = 0;
        if (s.length() % 2 == 0) {
            int mid = s.length() / 2;
            int p1 = mid - 1;
            int p2 = s.length() - 1;
            while (p1 >= 0 && p2 > mid - 1) {
                if (s.charAt(p1) == s.charAt(p2)) {
                    p1--;
                    p2--;
                    res++;
                } else { //如果不一致了，那么就不可能成为后缀
                    //res重置0
                    res = 0;
                    p1--;
                    //p2不要动。
                }
            }
        }
        if (s.length() % 2 != 0) {
            int mid = s.length() / 2;
            int p1 = mid - 1;
            int p2 = s.length() - 1;
            //奇数的话，p2移动的条件改一下就行
            while (p1 >= 0 && p2 > mid) {
                if (s.charAt(p1) == s.charAt(p2)) {
                    p1--;
                    p2--;
                    res++;
                } else { //如果不一致了，那么就不可能成为后缀
                    //res重置0
                    res = 0;
                    p1--;
                    //p2不要动。
                }
            }
        }
        return res;
    }
}
