package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 从英文中重建数字_2021_11_24
 * @Time 2021/11/24  9:35
 * 423. 从英文中重建数字
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 * 示例 1：
 *
 * 输入：s = "owoztneoer"
 * 输出："012"
 * 示例 2：
 *
 * 输入：s = "fviefuro"
 * 输出："45"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 */


/**
* 根据「英文单词中的字符唯一性」确定构建的顺序
* 具体的，zero 中的 z 在其余所有单词中都没出现过，我们可以先统计 zero 的出现次数，
 * 并构建 0；然后观察剩余数字，其中 eight 中的 g 具有唯一性，构建 8；
 * 再发现 six 中的 x 具有唯一性，构建 6；
 * 发现 three 中的 h 具有唯一性（利用在此之前 eight 已经被删除干净，词频中仅存在 three 对应的 h)，
 * 构建 3 ... 依次类推
 *
 * 24字节后端笔试手撕题
* */
public class 从英文中重建数字_2021_11_24 {
    @Test
    public void test() {
        String s = "owoztneoer";
        System.out.println(originalDigits(s));
    }

    /**
     * 注意题目保证s一定有唯一合法的解才能这样写。
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++; //统计a-z各个字母出现的次数
        }
        int[] nums = new int[10];//统计0-9的次数
        //根据数字的唯一性出现字符统计0-9出现的次数

        //现根据唯一字母就能确定的0,8,6,2
        nums[0] = chars['z' - 'a'];
        nums[8] = chars['g' - 'a'];
        nums[6] = chars['x' - 'a'];
        nums[2] = chars['w' - 'a'];

        nums[3] = chars['t' - 'a'] - nums[2] - nums[8]; //2和8都用过t
        nums[4] = chars['r' - 'a'] - nums[3] - nums[0]; //3和0都用过r
        nums[1] = chars['o' - 'a'] - nums[0] - nums[2] - nums[4]; //0,2,4都用过o
        nums[7] = chars['s' - 'a'] - nums[6];//6用过s
        nums[5] = chars['f' - 'a'] - nums[4];//4用过f
        nums[9] = chars['i' - 'a'] - nums[5] - nums[6] - nums[8];//5,6,8用过i
        StringBuilder sb = new StringBuilder();
        //由nums[i] 如{1,1,1,0,``````}来组成最后的数字串
        for (int i = 0; i < 10; i++) {//从0-9
            //nums[i]有几个就加几次
            for (int j = 0; j < nums[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
