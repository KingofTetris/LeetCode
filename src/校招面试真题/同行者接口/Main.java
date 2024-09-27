package 校招面试真题.同行者接口;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/9/27
 */
public class Main {
    public static void main(String[] args) {
        cfImpl impl1 = new cfImpl();
        hbImpl impl2 = new hbImpl();
        String s = "ab&amp;&amp;2&amp;&amp;as&amp;&amp;5656";
        String[] chaifen = impl1.chaifen(s);
        String hebing = impl2.hebing(chaifen);
        for (String s1 : chaifen) {
            System.out.println(s1);
        }
        System.out.println(hebing);
    }
}

/**
 不能使用语言的基本分割组合函数(如Java的String.split，php的explode和implode)
 1) 字符串拆分成数组，如”ab&amp;&amp;2”通过”&amp;&amp;”做分隔符，分割得到字符串数组[“ab”,”2”]
 2) 实现字符串组合，如[“ab”,”2”]通过”&amp;&amp;”分隔符，组合成字符串”ab&amp;&amp;2”

 作者：冒险团
 链接：https://www.nowcoder.com/feed/main/detail/3ddaca69c4bf43f0bf593ef531b5a480?sourceSSR=search
 来源：牛客网
 */
class cfImpl implements cf{
    public String[] chaifen(String s) {
        String mark = "&amp;&amp;";
        String fb = s;
        ArrayList<String> res = new ArrayList<>();
        int start = 0;
        while (fb.contains(mark)){
            int end = fb.indexOf(mark); //每次找到mark出现的位置
            res.add(fb.substring(start,end));//先添加，再去掉。
            fb = fb.replaceFirst(mark, "");//去掉第一个mark
            start = end;//更新start为下一个起始位置
        }
        //最后还要添加start到s.length
        res.add(fb.substring(start));

        String[] sRes = new String[res.size()];
        for (int i = 0; i < sRes.length; i++) {
            sRes[i] = res.get(i);
        }
        return sRes;
    }
}

class hbImpl implements hb{
    public String hebing(String[] s) {
        String mark = "&amp;&amp;";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            if (i != s.length - 1){
                sb.append(s[i]).append(mark);
            }
            else {
                sb.append(s[i]);
            }
        }
        return sb.toString();
    }
}
