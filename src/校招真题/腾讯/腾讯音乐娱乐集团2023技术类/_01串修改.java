package 校招真题.腾讯.腾讯音乐娱乐集团2023技术类;

/**
 * @Author KingofTetris
 * @Date 2023/3/31 10:26
 * 4.
 * 01串修改
 * 给定一个只包含'0'和'1'两种字符的字符串，每次操作可以选择相邻的两个字符，将它们同时变成'0'或者同时变成'1'。
 * 请问最少多少次操作后，所有的字符都相同？
 * <p>
 * 字符串长度不超过1000。
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 示例1
 * 输入例子：
 * "1001101"
 * 输出例子：
 * 2
 */
public class _01串修改 {
    public static void main(String[] args) {
        _01串修改 sc = new _01串修改();
        int i = sc.minOperations("000010");
        System.out.println(i);
    }
    //只包含01的字符串，每次可以修改相邻的两个字符为0或1，最少改多少次让整个字符串只有一种字符？

    /**
     *
     * 简单模拟一下修改成0和修改成1分别要多少次
     * 然后取小的那个就行了
     * @param str
     * @return
     */
    public int minOperations(String str) {
        int num1 = get_res(str,'0');
        int num2 = get_res(str,'1');
        return Math.min(num1,num2);
    }

    public int get_res(String str,char target){
        int ans = 0;//要修改的次数
        int idx = 0;
        char[] chars = str.toCharArray();
        while (idx < chars.length){
            if (target != chars[idx] ){ //如果当前位和target不一致就修改
                //如果相邻的下一位也不是target那一起改
                if (idx + 1 < chars.length){
                    chars[idx] = target;
                    chars[idx + 1] = target;
                }
                //如果是target，或者idx+1>n了，就改当前位置就行
                else chars[idx] = target;
                ans++;//增加一次修改次数
            }
            //下一位
            idx++;
        }

        return ans;
    }


}
