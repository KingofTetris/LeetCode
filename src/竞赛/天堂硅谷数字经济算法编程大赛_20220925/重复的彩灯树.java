package 竞赛.天堂硅谷数字经济算法编程大赛_20220925;

import LeetCode数据结构基础.day5.树.TreeNode;
import LeetCode数据结构基础.day5.树.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2022/9/26
 */

/**
 * 转化成字符串来判断
 */
public class 重复的彩灯树 {
        List<TreeNode> res;
        Map<String,TreeNode> items;
        Map<String,Integer> cnts;
        public List<TreeNode> lightDistribution(TreeNode root) {
            res=new ArrayList<>();
            items=new HashMap<>();
            cnts=new HashMap<>();
            dfs(root);
            return res;
        }

        public String dfs(TreeNode root){
            if(root==null) return "NULL,";
            String key=dfs(root.left)+dfs(root.right)+root.val+",";
            items.put(key,root);
            cnts.put(key,cnts.getOrDefault(key,0)+1);
            if(cnts.get(key)==2) //如果重复字符串有两串那么就是重复的结构。
                res.add(root);
            return key;
        }
}
