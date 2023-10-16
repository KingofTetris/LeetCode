package 校招笔试真题.建信金科;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2023/10/14
 */
public class 多少个子串 {

    @Test
    public void test() {
        String s = "ACBTMfxcc";
        System.out.println(countSeq(s));
    }

    public int countSeq(String mystr) {
        // write code here
        HashMap<Character, Integer> map = new HashMap<>();
        String lowerCase = mystr.toLowerCase();
        for (char c : lowerCase.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //至少要2个c,bft各1个
//        String target = "ccbft";
        int res = 0;
        Integer cValue = map.get('c');
        Integer bValue = map.get('b');
        Integer fValue = map.get('f');
        Integer tValue = map.get('t');
        while (cValue != null && cValue >= 2
                && bValue != null && bValue >= 1
                && fValue != null && fValue >= 1
                && tValue != null && tValue >= 1) {
            res++;
            cValue -= 2;
            bValue -= 1;
            fValue -= 1;
            tValue -= 1;
        }
        return res;
    }
}
