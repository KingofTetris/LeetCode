package 校招真题.携程.携程暑期实习20230525;

/**
 * @author by KingOfTetris
 * @date 2023/6/26
 */

/**
 * 使用字符数组代替字符串：在该程序中，使用了字符串来接收输入和进行子串的提取操作。
 * 字符串是不可变的，每次进行子串提取时都会创建新的字符串对象，导致额外的内存开销。
 * 可以改为使用字符数组来代替字符串，避免不必要的对象创建和内存拷贝。
 *
 * 使用整数代替字符：在提取尾巴的奇偶性时，使用了字符和字符数组之间的转换，
 * 并将字符转换为对应的整数。这些转换操作也会消耗额外的空间和时间。可以直接使用字符数组中的数字来进行奇偶性判断，
 * 避免字符到整数的转换。
 */

/**
 * 比较差的代码
 * //和为偶数 则奇数+奇数 偶数+偶数
 *     public static void main(String[] args){
 *         Scanner sc = new Scanner(System.in);
 *         //nextInt明显小于他的数据范围,所以你必然是要用字符串来接收的
 *         String input = sc.nextLine();
 *         //拆分允许出现前导0
 *         int res = solution(input);
 *         System.out.println(res);
 *     }
 *     public static int solution(String input){
 *         int count = 0;
 *         for(int p = 1;p < input.length();p++){
 *             String before = input.substring(0,p);
 *             String after = input.substring(p,input.length());
 *             int tailB = before.charAt(before.length() - 1);//取尾巴是奇数还是偶数
 *             int tailA = after.charAt(after.length() - 1);//取尾巴是奇数还是偶数
 *             if((tailA + tailB)%2==0){
 *                 count++;
 *             }
 *         }
 *         return count;
 *     }
 */

import java.util.Scanner;

public class _1303_游游的整数切割 {
    //和为偶数 则奇数+奇数 偶数+偶数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //nextInt明显小于他的数据范围,所以你必然是要用字符串来接收的
        String input = sc.nextLine();
        char[] chars = input.toCharArray();
        //拆分允许出现前导0
        int res = solution(chars);
        System.out.println(res);
    }

    //因为只分两部分，其实头尾是固定的
    public static int solution(char[] chars) {
        int count = 0;
        //注意p不能移动到最后一位。不然就重复了
        for (int p = 0; p < chars.length - 1; p++) {
            int tailB = chars[p] - '0'; // 前面部分的个位数
            int tailA = chars[chars.length - 1] - '0'; // 后面部分的个位数
            if ((tailA + tailB) % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
