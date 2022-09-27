package 程序员面试金典;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/27 11:26
 */
public class _01_05_一次编辑 {

    @Test
    public void test(){

        System.out.println(oneEditAway("", ""));
    }

    /**
     * 插入删除替换一个字母
     * 判断是否能让第一个字符串经过某个操作变成第二个
     *
     * 要一次操作就能完成
     * 那么必然字符长度的差异得等于1或者长度一样 只是同位置的某个字符不一样
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {

        int len1 = first.length();
        int len2 = second.length();

        if (Math.abs(len1 - len2) >= 2) return false;//长度差2是不可能一次操作相等的
        if (first.equals(second)) return true;//很怪，这个B题测试用例一样的字符 是返回true  题干里面藏了一个(或0次)。牛逼

        String longStr = len1 > len2 ? first : second;
        String shortStr = len1 > len2 ? second : first;
        if (len1 == len2){ //如果是长度一样的情况
            //比较是不是只有某个字符不一样
            int diff = 0;
            for (int i = 0; i < len1; i++) {
                if (longStr.charAt(i) != shortStr.charAt(i) ) diff++;
                if (diff == 2) return false;
            }

            return true;
        }

        //len1 和 len2 相差1的情况
        int count = 0;//差异情况
        int i = 0,j = 0;//两个指针
        for (; i < shortStr.length() && j < longStr.length();) { //i指向短的,j指向长的
//            if (count == 2) return false; //如果放在这里，可能会达到末尾，跳出循环不执行了。
            if (shortStr.charAt(i) == longStr.charAt(j)){
                i++;
                j++;
            }
            else {
                j++;
                count++;
                if (count == 2) return false; //判断要放在这里
            }
        }

        return true;
    }
}
