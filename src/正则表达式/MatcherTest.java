package 正则表达式;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author KingofTetris
 * @Date 2022/7/28 11:58
 * 简单的Java正则表达式Matcher类的用法
 */
public class MatcherTest {
    @Test
    public void test() {

        //下面的正则表达式的意思是
        //字符或者+-号重复0次1次或者多次，然后跟上% 和 数字重复0-无限次
        Pattern p = Pattern.compile("(\\w||\\-||\\+)+%(\\d+)"); // \w 包括下划线在内的单个字符，[A-Z a-z 0-9 _] \d 数字
        //所以ab%12-+cd%34按照这个规则就可以分为
        //ab%12 和 -+cd%34 两组
        Matcher m = p.matcher("ab%12-+cd%34");
        //Matcher.matches()、Matcher.lookingAt()、Matcher.find()
        //Matcher 类提供了三个匹配操作方法，三个方法均返回 boolean 类型，当匹配到时返回 true，没匹配到则返回 false 。
        //matches() 对整个字符串进行匹配，只有整个字符串都匹配了才返回true 。
        //lookingAt() 从头开始找，只有字符串的前缀匹配才返回true
        //find() 对字符串进行匹配，匹配到的字符串可以在任何位置。
        /**
         * 所以下面的输出是 F T T
         */
        System.out.println(m.matches());
        System.out.println(m.lookingAt());
        System.out.println(m.find());
//        System.out.println(m.groupCount());
        while (m.find()) {
            /**
             * 当使用matches()，lookingAt()，find()执行匹配操作后，就可以利用下面三个方法得到更详细的信息：
             * start()返回匹配到的子字符串的第一个字符在原字符串中的索引位置；
             * end()返回匹配到的子字符串的最后一个字符在原字符串中的索引位置；
             * group()返回匹配到的子字符串。
             * Mathcer 类还有一个groupCount()用于返回有多少组。
             * start()，end()，group()均有一个重载方法，
             * 它们是start(int i)，end(int i)，group(int i)专用于分组操作i就是对应的组，i从1开始
             * 每次执行匹配操作后start()，end()，group()三个方法的值都会改变,
             * 改变成匹配到的子字符串的信息，以及它们的重载方法，也会改变成相应的信息。
             */
            System.out.println("开始索引：" + m.start());// 开始索引：0
            System.out.println("group():" + m.group());// group():ab%12
//            m.reset();//不输入input则将索引指针重新指向0，这里就无限循环了。。
        }
        m.reset("ef%56-gh%78");//重置字符串
        while (m.find()) {
            System.out.println("开始索引：" + m.start());
            System.out.println("group():" + m.group());
        }
    }
}
