package 校招笔试真题.用友._20230918;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/18
 */
public class 传染者链条 {

    @Test
    public void test(){
        //5,[[3,4,2],[1,2,1],[2,3,1]],1
        //6,[[1,2,5],[2,3,8],[1,5,10]],1
//        int[][] meetings = {{3,4,2},{1,2,1},{2,3,1}};
        int[][] meetings = {{2,4,5},{1,2,5},{2,3,8},{1,5,10}};
        int n = 6;
        ArrayList<Integer> allPerson = findAllPerson(n, meetings, 1);
        System.out.println(allPerson);
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int整型 人员总数
     * @param meetings int整型二维数组 人员接触信息
     * @param firstPerson int整型 人员 0 在时刻 0 第一个接触到的人员编号
     * @return int整型ArrayList
     */
    public ArrayList<Integer> findAllPerson (int n, int[][] meetings, int firstPerson) {
        // write code here
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0); //0是第一个感染的
        res.add(firstPerson);//firstPerson是第二个
        //按照感染时刻从小到大排序
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < meetings.length; i++) {
            //不能一个一个取，要把同一个时刻的都拿出来。
            Set<Integer> set = new HashSet<>();
            int[] meeting = meetings[i];
            set.add(meeting[0]);
            set.add(meeting[1]);
            for (int j = i + 1; j < meetings.length; j++) {
                //如果是同一个时刻
                if (meetings[j][2] == meeting[2]){
                   set.add(meetings[j][0]);
                   set.add(meetings[j][1]);
                }else {
                    //因为已经排序了，如果不是就没必要继续遍历了。
                    break;
                }
            }
            //然后判断同一时刻里面有没用感染者
            for (Integer grz : res) {
                //如果包含一个感染者，所有人都要感染。
                if (set.contains(grz)){
                    for (Integer other : set) {
                        if (!res.contains(other)){
                            res.add(other);
                        }
                    }
                    //添加完成就可以break了。
                    break;
                }
            }
        }
        //从小到大排序。这么坑啊。不按顺序不对，差点少了60%
        res.sort(null);
        return res;
    }
}
