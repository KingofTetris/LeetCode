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
        while (t-- > 0){
            String s = sc.next();
//            solution(s,n);
        }
    }
    @Test
    public void test(){
        //找出每个字母交替出现的次数
        //比如下面A交替出现了2次，B交替出现2次，X1次，S1次。
        String s = "AAAABBAAAXXXBBS";
        //如果是DDDDD，则只有D交替出现1次
        //如果是DXXXXSSSDDDSKAXMMSKKD 则D交替出现3次，X2次，S3次，K2次，A1次，M1次。
        Map<Character, Integer> characterIntegerMap = countAlternatingCharacters(s);
        System.out.println(characterIntegerMap);
    }
    public static Map<Character, Integer> countAlternatingCharacters(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }
        for (Character c : set) {
            int nowIndex = str.indexOf(c + "", 0);
            while (true){

            }
        }
        return null;
    }
}
