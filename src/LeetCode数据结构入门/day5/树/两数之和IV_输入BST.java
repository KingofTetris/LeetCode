package LeetCode数据结构入门.day5.树;

import org.junit.Test;

import java.util.*;

/**
 * @author KingofTetris
 * @File 两数之和IV_输入BST
 * @Time 2021/10/9  11:56
 */

/*给定一个二叉搜索树 root 和一个目标结果 k，
        如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。*/
/*    二叉树的节点个数的范围是  [1, 10^4].
            -10^4 <= Node.val <= 10^4
            root 为二叉搜索树
            -10^5 <= k <= 10^5  */
public class 两数之和IV_输入BST {
    @Test
    public void test(){
//        CreateTree ct = new CreateTree();
        TreeNode tree = TreeUtils.createTree(new Integer[]{4,2,7,1,3,5,8});
        System.out.println(findTarget(tree, 14));
    }


    //set+先序遍历 不用层序那么多代码
    //但时间复杂度和空间复杂度并没有说就更好。
    Set<Integer> set = new HashSet<>();
    public boolean findTarget3(TreeNode root,int k){
       if (root == null) return false;
       if (set.contains(k - root.val)) return true;
       set.add(root.val);
       //先序遍历根左右
       return findTarget3(root.left,k) || findTarget3(root.right,k);
    }



    //法一 推荐 层序+Map
    //因为BST本来就不存在重复元素，MAP就行了
    // 把所有的val放入集合Map中 用层序遍历
    // 直接用集合的contains方法判断有没用k - node.val 就行了
    public boolean findTarget(TreeNode root, int k) {
        Map<Integer,Boolean> map = new HashMap<>();

        //层序遍历
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode t = queue.poll();
                //先判断是否有存在k - t.val这个节点
                //存在就是true
                if(map.getOrDefault(k-t.val,false)) return true;

                //把每个节点的节存进map。
                map.put(t.val,true);
                if (t.left!=null) queue.offer(t.left);
                if (t.right!=null) queue.offer(t.right);
            }
        }
        return false;
    }


    //法二
    //中序遍历转换成升序数组 利用有序的特点然后用双指针法 但这个方法耗时太严重了。
    //一个头指针 一个尾指针
    //两个相加 大了就把尾指针前移，小了就把头指针后移。
    //当前后相遇的时候就返回false
    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();
        inorder(root,list);
        int pre = 0;
        int tail = list.size() - 1;
        while(pre != tail){
            int target = list.get(pre) + list.get(tail);
            if(target > k){
                tail--;
            }
            if (target < k){
                pre++;
            }
            if (target == k) return true;
        }
        //遍历完都没找着就没有了
        return false;
    }

    public void inorder(TreeNode root,List<Integer> list){
//        如果节点为空就不用加进去了
        if (root == null)
            return;
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }
}
