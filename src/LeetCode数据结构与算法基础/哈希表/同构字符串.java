package LeetCode数据结构与算法基础.哈希表;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 同构字符串 {

    @Test
    public void test() {
        String a = "abcdefghijklmnopqrstuvwxyzva";
        String b = "abcdefghijklmnopqrstuvwxyzck";
        String[] aa = a.split("");
        String[] bb = b.split("");
        String aVal = wordPatternHelper(aa);
        String bVal = wordPatternHelper(bb);
        System.out.println(aVal);
        System.out.println(bVal);
        //你会发现这样的做法下，va和ck的翻译是一样的，没有唯一性了
        //因为上面是v=21,a = 0
        //下面是c= 2,k=10
        //合起来都是210。
        //那么你在每个字符中间加个特殊符号_来表示分割就行了。
        boolean isomorphic = isIsomorphic(a, b);
        System.out.println(isomorphic);
    }


    //add egg
    //paper title 都是一样的形式
    //所以叫做同构，foo bar不一样就不是同构
    //这道题实际上和单词规律差不多。基本上代码都一样。
    public boolean isIsomorphic(String s, String t) {
        return wordPatternHelper(s.split("")).equals(wordPatternHelper(t.split("")));
    }

    private String wordPatternHelper(String[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            //已经翻译过了
            if (map.containsKey(arr[i])) {
                sb.append(map.get(arr[i])).append("_");
            } else {
                sb.append(count).append("_");
                //arr[i] -> count 映射
                map.put(arr[i], count);
                count++;
            }
        }
        return sb.toString();
    }

    //上面那种两边翻译的做法，效率真的太低了，两边都要翻译，而且要保证唯一性，
    //最后还要比较两个字符串是否相等。emm....

    //钻研一下高效的解法，双射(双射：既是单射又是满射，又称为一一对应。)
    public boolean isIsomorphic2(String s, String t) {
        //字符出现的位置要一样，次数要一样，才叫同构
        //题目保证s t长度一样，就不用多此一举了。
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            // 对于已有映射 a -> s2t[a]，若和当前字符映射 a -> b 不匹配，
            // 说明有一对多的映射关系，则返回 false ；
            // 对于映射 b -> a 也同理
            if (s2t.containsKey(a) && s2t.get(a) != b) return false;
            if (t2s.containsKey(b) && t2s.get(b) != a) return false;
            s2t.put(a, b);
            t2s.put(b, a);
        }
        return true;
     /*   作者：Krahets
        链接：https://leetcode.cn/problems/isomorphic-strings/solutions/1645867/by-jyd-i4wt/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
