package LeetCode数据结构与算法基础.day6.图;

/**
 * @author KingofTetris
 * @File 找到小镇的法官
 * @Time 2021/10/28  10:11
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。

        如果小镇的法官真的存在，那么：

        小镇的法官不相信任何人。
        每个人（除了小镇法官外）都信任小镇的法官。
        只有一个人同时满足条件 1 和条件 2 。
        给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示编号为 a 的人信任编号为 b 的人。
        如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。
        示例 1：

        输入：n = 2, trust = [[1,2]]
        输出：2
        示例 2：
        输入：n = 3, trust = [[1,3],[2,3]]
        输出：3
        示例 3：
        输入：n = 3, trust = [[1,3],[2,3],[3,1]]
        输出：-1
        示例 4：
        输入：n = 3, trust = [[1,2],[2,3]]
        输出：-1
        示例 5：
        输入：n = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
        输出：3
        提示：
        1 <= n <= 1000
        0 <= trust.length <= 104
        trust[i].length == 2
        trust[i] 互不相同
        trust[i][0] != trust[i][1]
        1 <= trust[i][0], trust[i][1] <= n*/


//一个法官问题，如果有多个法官呢？也就是除了法官外的人都信任法官，法官谁也不信。怎么找出所有法官？
public class 找到小镇的法官 {

    @Test
    public void test(){
        int n = 2;
//        int[][] trust = {{1,3},{1,4},{2,3},{2,4},{4,3}};
        int[][] trust = {};
        System.out.println(findJudge(n, trust));
    }
    //如果有法官，则法官的编号一定出现n-1次，并且法官不相信任何人
    public int findJudge(int n, int[][] trust) {

        //边界条件,如果只有一个人，且他不信任何人。那他一定是法官。
        if (trust.length == 0 && n == 1)
            return 1;

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < trust.length; i++) {
                map.put(trust[i][1],map.getOrDefault(trust[i][1],0) + 1);
        }

        int isJudge = -1;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if (entry.getValue() == n-1)
                //并且要key不在trust[i][0]中出现
                isJudge = entry.getKey();
        }

        //最后排除相信其他人的情况
        for (int i = 0; i < trust.length; i++) {
            if (isJudge == trust[i][0])
                return -1;
        }
        return isJudge;
    }

    //法二 投票法
    public int findJudge2(int n, int[][] trust) {
        // 定义数组用于存放每个人的信任值
        int[] trustValues = new int[n + 1]; // 人员编号从1开始，这里+1防止后续计算麻烦

        // 遍历trust数组计算每个人的信任值
        for(int[] t : trust){
            //出现在t[0]中必然不是法官
            //因为法官不信其他人
            trustValues[t[0]]--;

            //如果有一个人被其他所有人相信，那就是法官
            trustValues[t[1]]++;
        }
        // 遍历这n个人的信任值，如果存在 n - 1，则返回这个人的编号
        for(int i =1; i <= n;i++){
            if(trustValues[i] == (n - 1)) return i;
        }
        return -1;
    }

    //法三 图
}
