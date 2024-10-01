package LeetCode数据结构与算法基础.数学;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/10/1
 */
public class 格雷编码 {

    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            //第i个格雷编码= i/2 ^ i
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }
}
