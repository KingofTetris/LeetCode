package 校招笔试真题.深信服;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 */
public class 可信终端 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        while (t-- > 0) {
            String s = sc.next();
//            solution(s,n);
        }
    }

    @Test
    public void test() {
        //找出每个字母交替出现的次数
        //比如下面A交替出现了2次，B交替出现2次，X1次，S1次。
//        String s = "AAAABBAAAXXXBBS";
        //如果是DDDDD，则只有D交替出现1次
        //如果是DXXXXSSSDDDSKAXMMSKKD 则D交替出现3次，X2次，S3次，K2次，A1次，M1次。
        String s = "DXXXXSSSDDDSKAXMMSKKD";
        int n = 3;
        int res = countAlternatingCharacters(s, n);
        System.out.println(res);
    }

    public static int countAlternatingCharacters(String str, int n) {
        Map<Character, Integer> countMap = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }
        for (Character c : set) {
            int nowIndex = str.indexOf(c + "", 0);
            while (true) {
                //nextIndex从nowIndex开始往后移动
                int nextIndex = str.indexOf(c + "", nowIndex + 1);
                if (nextIndex - nowIndex == 1) {
                    nowIndex = nextIndex;//没有交替继续后移。
                } else { //如果不等1，也就是>1或者等于0只出现一次，也算交替1次。也要+1s
                    nowIndex = nextIndex;//交替了也要更新!
                    countMap.put(c, countMap.getOrDefault(c, 0) + 1);
                }
                if (nextIndex == -1) break;//如果字符串里面已经没有c了，跳出。
            }
        }
        System.out.println(countMap);
        //有了交替次数map 接下来就很简单了。
        int res = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= n) {
                res++;
            }
        }
        return res;
    }
}
