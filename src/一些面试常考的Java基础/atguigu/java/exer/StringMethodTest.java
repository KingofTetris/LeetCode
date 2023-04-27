package 一些面试常考的Java基础.atguigu.java.exer;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

public class StringMethodTest {

    @Test
    public void test(){
        String s = new String("good");
        String t = new String("Good");
        String temp = "hello,world";
        System.out.println(s.length());
        System.out.println(s.charAt(0));
        System.out.println(s.isEmpty());
        System.out.println(s.toLowerCase(Locale.ROOT));
        System.out.println(s.toUpperCase(Locale.ROOT));
        s += "   ";
        System.out.println(s.trim());
        System.out.println(s.equalsIgnoreCase(t));
        System.out.println(s.equals(t));
        System.out.println(s.substring(1));
        System.out.println(s.substring(1, 2));
        System.out.println(s.endsWith("ood"));
        System.out.println(s.startsWith("go"));
        System.out.println(s.startsWith("oo", 1));
        System.out.println(temp.contains("wo"));
        System.out.println(temp.indexOf("worl"));//返回第一个出现的位置，没找到返回-1
        temp.indexOf("wo",4);//也一样，只不过是从fromindex开始往后找，前面的不算
        temp.lastIndexOf("wo");//从后往前找
        temp.lastIndexOf("wo",2);//

        String str = "12345";

        System.out.println(str.matches("\\d+"));
        // \d表示数字0-9，+代表出现次数>1次

        String tel = "0591-4533233";//福州固定电话
        System.out.println(tel.matches("0591-\\d{7,8}"));

    }

    @Test
    public void test2(){
        /**
         * String 与 char[] 之间转换
         * String ----> char[] : String.toCharArray();
         * char[] ----> String : 构造器
         */
        String str = "abc123"; //题目 把abc123 反转成 a21cb3
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
        String s = new String(chars);

        System.out.println();
        System.out.print(chars[0]);
        for (int i = chars.length - 2; i >=1; i--) {
            System.out.print(chars[i]);
        }
        System.out.print(chars[chars.length - 1]);

    }

    @Test
    public void test3(){
        /**
         * String 和 byte[] 转换
         * String ---> byte[] : String.getBytes();
         * byte[] ---> String : 调用String构造器
         *
         * 编码：字符串--->字节
         * 解码：字节 -----> 字符串
         *
         * 说明：解码时，必须与解码时的字符集一致，否则会乱码
         */

        String str = "abc123中国";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));
        String temp = new String(bytes);
        System.out.println(temp);

        try {
            byte[] gbks = str.getBytes("gbk");
            System.out.println(Arrays.toString(gbks));
            temp = new String(gbks,"unicode");
            System.out.println(temp);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
