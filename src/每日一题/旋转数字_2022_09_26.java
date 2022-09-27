package 每日一题;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author KingofTetris
 * @Date 2022/9/26 9:41
 */
public class 旋转数字_2022_09_26 {


    @Test
    public void test(){
        int i = rotatedDigits(857);
        System.out.println(i);
    }

    //只有那些只包含2，5，6，9的数字旋转后
    //仍然是有效且改变的。
    //但要注意的是如果仅出现0，1，8也是不行的
    // 0，1，8和2，5，6，9一起出现，那么也是好数
    public int rotatedDigits(int n) {
        int count = 0 ;
        for (int i = 1; i <= n; i++) {
            if (vaild(i)) count++;
        }
        return count;
    }

    public boolean includeNumbers(int num){
        int digit = num % 10;
        int digitNum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //因为key的数量是固定的 0 - 9
        //避免下面get空指针异常
        for (int i = 0; i < 10; i++) {
            map.put(i,0);//全部先初始化为0
        }
        while (num != 0 ){
            map.put(digit,map.get(digit) + 1);//记录每个数字出现的次数
            num = num / 10;
            digitNum++;//记录这个数有几位
            digit = num % 10;
        }

        int flag = map.get(0) + map.get(1) + map.get(8);
        if (map.get(3) != 0 || map.get(4) != 0 || map.get(7) != 0) return false;//3,4,7反转后是无效的数字
        else if (map.get(0) == digitNum || map.get(1) == digitNum || map.get(8) == digitNum || flag == digitNum) return false;//全是0，1，8
        //其他情况都是true
        return true;
    }


    /**
     * 直接用数组标记
     * 判断镜像？有效？
     */
    boolean rev[] = { true,true,true,false,false,true,true,false,true,true }; // rev[i]表示数字i旋转后是否是有效的
    boolean mir[] = { false,false,true,false,false,true,true,false,false,true }; // mir[i]表示数字i旋转后是否是镜像的
    boolean vaild(int x) {
        boolean mirror = false;
        while (x != 0) {
            int d = x % 10;
            mirror |= mir[d]; //一旦出现一个镜像数字，mirror就成为true
            if (!rev[d]) return false; //如果旋转后无效，直接返回false
            x /= 10;
        }
        return mirror; //返回是否含有镜像数字
    }

    /*作者：bac0id
    链接：https://leetcode.cn/problems/rotated-digits/solution/shu-zu-de-miao-yong-by-bac0id-2e90/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
