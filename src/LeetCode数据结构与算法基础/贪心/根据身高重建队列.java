package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2024/9/10
 */
public class 根据身高重建队列 {


    /**
     * 其实如果大家认真做了135. 分发糖果 (opens new window)，就会发现和此题有点点的像。
     *
     * 在135. 分发糖果 (opens new window)我就强调过一次，遇到两个维度权衡的时候，
     * 一定要先确定一个维度，再确定另一个维度。
     *
     * 如果两个维度一起考虑一定会顾此失彼。
     *
     * 对于本题相信大家困惑的点是先确定k还是先确定h呢，也就是究竟先按h排序呢，还是先按照k排序呢？
     *
     * 如果按照k来从小到大排序，排完之后，会发现k的排列并不符合条件，身高也不符合条件，两个维度哪一个都没确定下来。
     *
     * 那么按照身高h来排序呢，身高一定是从大到小排（身高相同的话则k小的站前面），让高个子在前面。
     *
     * 此时我们可以确定一个维度了，就是身高，前面的节点一定都比本节点高！
     *
     * 那么只需要按照k为下标重新插入队列就可以了，为什么呢？
     * @param people
     * @return
     */

    @Test
    public void test(){
        int[][] nums = {{7,0}, {4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] queue = reconstructQueue(nums);
        for (int[] q : queue) {
            System.out.print(Arrays.toString(q) + ", ");
        }
    }
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        int m = people[0].length;
        LinkedList<int[]> list = new LinkedList<>();
        //优先按照身高从大到小排序
        Arrays.sort(people,(o1, o2) ->{
            int ans = o2[0] - o1[0];
            //如果身高一样，按照ki从小到大排序，先排小的再排大的。
            if (ans == 0) return o1[1] - o2[1];
            //先按照身高从大到小排序
            else return ans;
        });
        //然后按照规则往list里面插入
        list.add(people[0]);
        for (int i = 1; i < people.length; i++) {
            if (people[i][1] == i){
                //如果前面刚好有i个比他高的就不用动直接往后面插入
                list.add(people[i]);
            }
            //如果前面有i个比他高的，但是他的要求比i小，那么就要把他往前挪
            else if (people[i][1] < i){
                //add(index,obj)
                list.add(people[i][1],people[i]);
            }
            //如果前面有i个比他高的，但是他的要求比i大。
            //这种情况是不会发生的，这种不可能建成合法队列
            //题目保证不会发生这种情况
        }
        int[][] res = new int[n][m];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
