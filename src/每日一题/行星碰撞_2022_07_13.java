package 每日一题;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/7/13 14:17
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。
 * 每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，
 * 较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。
 * 两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 *  
 *输入：
 * [-2,-1,1,2]
 * 输出：
 * []
 * 预期结果：
 * [-2,-1,1,2]
 *
 * 提示：
 *
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/asteroid-collision
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 行星碰撞_2022_07_13 {
    @Test
    public void test(){
        int[] asteroids = {2,-1,-1,-2};  //这两个例子好像入魔了，一定只有一个能成立。
//        int[] asteroids = {5,10,-5};
        int[] ints = collisionAsteroids(asteroids);
        for (int anInt : ints){
            System.out.print(anInt + "\t");
        }
    }
    /**
     * 绝对值是大小，正负是方向。假设同向不会发生碰撞
     * 而且明确了正表示向右移动，负表示向左移动！！！！！！！操！白做！
     * 你有没有想过如果正负只是反方向，那么一定只留下那个绝对值最大的，还有什么意义。。。
     * 有点像大鱼吃小鱼
     * 找到相邻的正负数 判断大小，不断重复这个过程,直至没有碰撞
     *
     * 淦啊！！卡在272 / 275 了！
     *
     * 操。。耗时2小时，终于解决了。。。这个数据emm.........
     * 执行用时：44 ms, 在所有 Java 提交中击败了5.76%的用户
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了5.03%的用户
     */
    public int[] collisionAsteroids(int []asteroids){
        boolean flag = false; //碰撞标志，初始判定为false
        LinkedList<Integer> aster_List = new LinkedList<>();
        for (int i = 0; i < asteroids.length;i++) {
            aster_List.add(asteroids[i]);
        }
        for (int i = 0; i < aster_List.size() - 1;) {
            int left = aster_List.get(i);
            int right = aster_List.get(i+1);
            if (left > 0 && right < 0){  //左边的为正，右边的为负才会发生碰撞而不是简单的异号。
                flag = true; //如果发生了碰撞就标记为true
                int sum = left + right;//两数相加判断正负
                if (sum > 0){//sum > 0 则正数比较大，留下正数
                    if (left>0){
                        aster_List.remove(i+1);
                    }
                    if (right>0){
                        aster_List.remove(i);
                    }
                }

                if (sum < 0){ //sum < 0 则负数比较大，留下负数
                    if (left < 0){
                        aster_List.remove(i+1);
                    }
                    if (right < 0){
                        aster_List.remove(i);
                    }
                }
                if (sum == 0){ //如果等于0，那么两个行星毁灭
                    aster_List.remove(i + 1);//注意要先删除i+1 小细节很重要，不然remove(i)再remove(i+1)会bug 因为链表长度是实时变化的
                    aster_List.remove(i);
                }
            }

            /**
             * 发生碰撞后呢？应当判断是否产生了新的碰撞,而不是直接i++
             */
            if (flag == true ){ //flag为true则说明发生了碰撞 就需要继续检验
                flag = false;//先把标记改为false,因为重新检验你也不知道会不会发生碰撞
                if (i==0) {} //i等于0什么都不做。把i留在原地
                else i--; //i>0 退回上一个位置重复进行这个过程
            }
            else if (flag == false){ //检测后flag为false说明无碰撞向后推
                i++;
            }
        }
        int[] res = new int[aster_List.size()];
        for (int i = 0; i < aster_List.size(); i++) {
            res[i] = aster_List.get(i);
        }
        return res;
    }

    /**
     * 官方题解 利用栈
     * @param asteroids
     * @return
     *
     * 使用栈 st 模拟行星碰撞，从左往右遍历行星数组 asteroids，当我们遍历到行星 aster 时，
     * 使用变量 alive 记录行星 aster 是否还存在（即未爆炸）。
     *
     * 当行星 aster 存在且 aster<0，(此时aster向左)
     * 栈顶元素非空且大于 0 时，(aster的前一个行星向右) 这是唯一的情况两个行星会发生碰撞：
     * 如果栈顶元素大于等于 -aster，则行星 aster 发生爆炸，将alive 置为 false；
     * 如果栈顶元素小于等于 -aster，则栈顶元素表示的行星发生爆炸，执行出栈操作。
     * 重复以上判断直到不满足条件，如果最后 alive 为真，说明行星 aster 不会爆炸，则将 aster 入栈。
     *
     *
     * 需要和上一个元素比较的通常可以联系到栈
     * * 执行用时：2 ms, 在所有 Java 提交中击败了96.89%的用户
     * * 内存消耗：42.2 MB, 在所有 Java 提交中击败了78.03%的用户
     */
    public int[] collisionAsteroids2(int []asteroids){
        Deque<Integer> stack = new ArrayDeque<>();
        for (int aster : asteroids) {
            boolean alive = true; //标记行星的状态

            while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                //当行星 aster 存在且 aster<0，栈非空且栈顶>0 时，说明两个行星相互向对方移动
                alive = stack.peek() < -aster; // 如果前一个正数小于负数的绝对值，那alive 为true，不然就为false
                if (stack.peek() <= -aster) {  // 栈顶行星爆炸
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(aster); //行星没爆炸就入栈
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
