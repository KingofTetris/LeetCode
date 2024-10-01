package LeetCode数据结构与算法基础.模拟;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/9 9:35
 *
 * 每当用户执行变更文件夹操作时，LeetCode 文件系统都会保存一条日志记录。
 *
 * 下面给出对变更操作的说明：
 *
 * "../" ：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则 继续停留在当前文件夹 。
 * "./" ：继续停留在当前文件夹。
 * "x/" ：移动到名为 x 的子文件夹中。题目数据 保证总是存在文件夹 x 。
 * 给你一个字符串列表 logs ，其中 logs[i] 是用户在 ith 步执行的操作。
 *
 * 文件系统启动时位于主文件夹，然后执行 logs 中的操作。
 *
 * 执行完所有变更文件夹操作后，请你找出 返回主文件夹所需的最小步数 。
 *
 * 示例 1：
 *
 * 输入：logs = ["d1/","d2/","../","d21/","./"]
 * 输出：2
 * 解释：执行 "../" 操作变更文件夹 2 次，即可回到主文件夹
 * 示例 2：
 *
 * 输入：logs = ["d1/","d2/","./","d3/","../","d31/"]
 * 输出：3
 * 示例 3：
 *
 * 输入：logs = ["d1/","../","../","../"]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= logs.length <= 10^3
 * 2 <= logs[i].length <= 10
 * logs[i] 包含小写英文字母，数字，'.' 和 '/'
 * logs[i] 符合语句中描述的格式
 * 文件夹名称由小写英文字母和数字组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/crawler-log-folder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 文件夹操作日志搜集器_2022_09_09 {

    @Test
    public void test(){
    }
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
                continue;
            } else if ("../".equals(log)) {
                if (depth > 0) {
                    depth--;
                }
            } else {
                depth++;
            }
        }
        return depth;
    }

    /**
     * 正则训练 但是正则匹配的操作的复杂度是O(k) k是分组按照正则表达式分组的个数
     * 那么这个方法的复杂度就是O(nk) 所以不如上面直接O(n)
     * 并且这里还需要开辟新的内存用于存储字符串。所以怎么都不如上面
     * @param logs
     * @return
     */
    public int minOperations2(String[] logs) {
        String rex = "\\w+/$";//\w 包含 a-z A-Z 0-9 别忘了还有下划线
        int depth = 0;
        for (int i = 0; i < logs.length; i++) {
            if (logs[i].matches(rex)) depth++;
            if ("../".equals(logs[i]) && depth > 0) depth--;
            if ("./".equals(logs[i])) continue;
        }
        return depth;
    }
}
