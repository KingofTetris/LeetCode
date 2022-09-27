package 每日一题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author KingofTetris
 * @Date 2022/7/27 16:34
 *
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
 *
 * 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，
 * 例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 *
 * 示例 1:
 *
 * 输入: expression = "-1/2+1/2"
 * 输出: "0/1"
 *  示例 2:
 *
 * 输入: expression = "-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 *
 * 输入: expression = "1/3-1/2"
 * 输出: "-1/6"
 *  
 *
 * 提示:
 *
 * 输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
 * 输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 分数加减运算_2022_07_27 {

    @Test
    public void test(){
        String express = "-1/2-1/2-1/2";
        System.out.println(fractionAddition2(express));
    }

    /**
     * 单纯的暴力模拟，没用到题目的条件 每个分数的分子与分母的范围是  [1,10]
     * 这个函数可以应用到分子分母是多位的情况，但是效率很低，O(n^2)。
     * @param expression
     * @return
     */
    public String fractionAddition(String expression) {
        //得到表达式里的数
        char[] chars = expression.toCharArray();
        LinkedList<Integer> fenzi = new LinkedList<>();
        LinkedList<Integer> fenmu = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        int j = 1;//记录分子部分直到 遇到"/"
        int k = 0;//记录分母部分 直到遇到"+" "-"
        int numTwo = 0;
        /**
         * 遍历前有个特例，以数字开头。还要处理两位数的10
         * 处理数字开头的情况
         */
        if (chars[0] == '-'){ //如果是负数开头
            while (chars[j] != '/'){ //不是'/'就全部加进来
                sb.append(chars[j]);
                j++;
            }
            fenzi.add(-Integer.parseInt(sb.toString()));//把-123..加进去分子
            sb.setLength(0);//清空内容
            k = j + 1;// j是"/"号了，那么后面的部分就是分母
            /**
             * k < chars.length要放前面，因为条件里有取出chars[k]的操作，如果放后面可能会数组越界。
             * 放前面起到短路的作用，避免越界
             */
            while (k < chars.length && chars[k] != '+' && chars[k] != '-' ){ //k要小于chars.length
                sb.append(chars[k]);
                k++;
            }
            fenmu.add(Integer.parseInt(sb.toString()));
            sb.setLength(0);
            j = 1;
            numTwo = k;//记录下第二数的开始位置
            k = 0;
        }
        if (chars[0] != '-'){ //如果是数字开头
            while (chars[j - 1] != '/'){ //不是'/'就全部加进来
                sb.append(chars[j - 1]);
                j++;
            }
            fenzi.add(Integer.parseInt(sb.toString()));//把123..加进去分子
            sb.setLength(0);//清空内容
            k = j ;// j - 1是"/"号了，那么后面的部分就是分母
            while (k < chars.length && chars[k] != '+' && chars[k] != '-' ){ //k要小于chars.length
                sb.append(chars[k]);
                k++;
            }
            fenmu.add(Integer.parseInt(sb.toString()));
            sb.setLength(0);//清空内容
            j = 1;
            numTwo = k;//记录下第二数的开始位置
            k = 0;
        }


        /**
         * 已经处理了开头的特殊情况 从k开始。k是第二个数的正负号 或者就已经到末尾了
         */
        for (int i = numTwo; i < chars.length; i++) {
            if (chars[i] == '-'){
                while (chars[j + i] != '/'){ //不是'/'就全部加进来
                    sb.append(chars[j + i]);
                    j++;
                }
                fenzi.add(-Integer.parseInt(sb.toString()));//把-123..加进去分子
                sb.setLength(0);//清空内容
                k = i + j + 1;// i + j是"/"号了，那么后面的部分就是分母
                while (k < chars.length && chars[k] != '+' && chars[k] != '-'){ //k要小于chars.length
                    sb.append(chars[k]);
                    k++;
                }
                fenmu.add(Integer.parseInt(sb.toString()));
                sb.setLength(0);
                j = 1;
                k = 0;
            }

            if (chars[i] == '+'){
                while (chars[j + i] != '/'){ //不是'/'就全部加进来
                    sb.append(chars[j + i]);
                    j++;
                }
                fenzi.add(Integer.parseInt(sb.toString()));//把123..加进去分子
                sb.setLength(0);//清空内容
                k = i + j + 1;// i + j是"/"号了，那么后面的部分就是分母
                while (k < chars.length && chars[k] != '+' && chars[k] != '-' ){ //k要小于chars.length
                    sb.append(chars[k]);
                    k++;
                }
                fenmu.add(Integer.parseInt(sb.toString()));
                sb.setLength(0);
                j = 1;
                k = 0;
            }
        }

        int fm = 1;
        for (int i = 0; i < fenmu.size(); i++) {
            //分母通分.分子相加减
            fm = fm * fenmu.get(i);//把所有分母都乘起来
        }

        for (int i = 0; i < fenzi.size(); i++) {
            //分子相加减 分子乘以除了对应的分母外的数的乘积 也就是 fm / fenmu.get(i)
            fenzi.set(i,fenzi.get(i) * (fm / fenmu.get(i)));
        }

        int fz = 0;
        for (int i = 0; i < fenzi.size(); i++) { //更新完了分子，算分子总和
            fz = fz + fenzi.get(i);
        }
        /**
         * 现在有了分子分母就可以求最大公约数，同时除以最大公约数就是最简分数了
         */
        int gcd = gcd(fz,fm);
        //分子分母异号，可能会产生负数gcd 所以最后要把负号调整一下位置
        System.out.println(fz + "/" + fm + ",gcd=" + gcd);
        fz = fz / gcd;
        fm = fm / gcd;
        if (fm < 0) {
            fz = -fz;
            fm = -fm;//同乘以个负号，把负号给分子
        }
        return fz + "/" + fm;
    }

    /**
     * 如果学过简单的正则表达式，可以用这个
     * @param strs
     * @return
     */
    public String fractionAddition2(String strs) {
        List<String> list = new ArrayList<>();
        String pattern = "-?\\d+\\/\\d+";// -号可以出现1次或0次? 然后出现1个或多个数字 中间一个/号 再出现一个或多个数字
        //这样就可以用正则表达式分割分式的效果
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(strs);
        while(m.find()){
            System.out.println(m.group());
            list.add(m.group());
        }
        int fz = 0;
        int fm = 1;
        int fzTemp;
        int fmTemp;
        int maxI;
        for(String l:list){
            String[] nums = l.split("/");//每组分式用/隔开分子分母
            fzTemp = Integer.valueOf(nums[0]);
            fmTemp = Integer.valueOf(nums[1]);
            maxI = gcd(fm , fmTemp);
            fz = (fz*fmTemp)/maxI + (fzTemp*fm)/maxI;
            fm = fmTemp*fm/maxI;
        }

        if(fz == 0) fm = 1;
        maxI = gcd(Math.abs(fz),fm);
        fz = fz/maxI;
        fm = fm/maxI;
        return fz + "/" + fm;
    }


    /**
     * 辗转取余法求最大公约数
     * 两数a,b一直辗转求余remainder，只要余数remainder不为0，
     * 就让a = b,b = remainder, remainder = a %b;
     * 当remainder为0时，则b就是最大公约数
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a,int b){
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }
}
