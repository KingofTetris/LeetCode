package 校招笔试真题.vivo._20230912;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/12
 */
public class 数组划分 {
    @Test
    public void test(){
       /* int[] nums = {1,2,2,6,3,4,5,3,3,3,9,1,1,1};
        List<List<Integer>> partition = partition(nums,5);
        for (List<Integer> list : partition) {
            System.out.println(list);
        }
        //现在要得到partition里面的和最小值即可
        int min = Integer.MAX_VALUE;
        for (List<Integer> list : partition) {
            int sum = list.stream().mapToInt(x -> x).sum();
            min = Math.min(min,sum);
        }
        System.out.println(min);*/
        int[][] tasks = {
                {1,1,5,2},
                {3,3,2,1},
                {8,3,9,1}
        };
        System.out.println(solution(tasks, 3));
    }
    public int solution(int[][] tasks,int n){
        int res = 0;
        for (int[] task : tasks) {
            List<List<Integer>> partition = partition(task, n);
            int min = Integer.MAX_VALUE;
            for (List<Integer> list : partition) {
                int sum = list.stream().mapToInt(x -> x).sum();
                min = Math.min(min,sum);
            }
            res += min;
        }
        return res;
    }
    public List<List<Integer>> partition(int[] project, int n) {
        List<List<Integer>> res = new LinkedList<>();
        Integer[] nums = new Integer[project.length];
        for (int i = 0; i < project.length; i++) {
            nums[i] = project[i];
        }
        //每次把大的先划分给时间最短的人
        //倒着排。
        Arrays.sort(nums, Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            res.add(new LinkedList<>());
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = minSumList(res);
            list.add(nums[i]);
        }
        return res;
    }

    public List<Integer> minSumList(List<List<Integer>> res) {
        int min = Integer.MAX_VALUE;
        for (List<Integer> re : res) {
            int sum = re.stream().mapToInt(x -> x).sum();
            min = Math.min(min,sum);
        }
        for (List<Integer> re : res) {
            int sum = re.stream().mapToInt(x -> x).sum();
            if (min == sum){
                return re;
            }
        }
        return null;
    }
}
