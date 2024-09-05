package 校招笔试真题.用友._20230918;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/18
 */
public class 传染者链条 {

    @Test
    public void test() {
        //5,[[3,4,2],[1,2,1],[2,3,1]],1
//        6,[[1,2,5],[2,3,8],[1,5,10]],1
        int[][] meetings = {{3, 4, 2}, {1, 2, 1}, {2, 3, 1},
                {4, 5, 0}, {3, 5, 5}, {5, 6, 5}, {6, 7, 11}};

        //给定一个下标从0开始的二维数组 meetings，
        // 其中 meetings[i]=[xi,yi,ti] 表示人员xi和yi在时刻ti有过接触；
        // 在 meetings 代表的所有接触发生期间，感染者会一直处于感染状态；

        //在meetings代表的所有接触都发生之后，请返回感染病毒V的所有感染者的编号列表，
        // 并按照从小到大的顺序记录人员编号
//        int[][] meetings = {{2,4,5},{1,2,5},{2,3,8},{1,5,10}};
        //给定一个正整数n，代表n 个人，这些人的编号从0到n-1；
        int n = 6;
        //firstPerson 第一个感染者
        int firstPerson = 5;
        ArrayList<Integer> allPerson = findAllPerson(n, meetings, firstPerson);
        System.out.println(allPerson);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n           int整型 人员总数
     * @param meetings    int整型二维数组 人员接触信息
     * @param firstPerson int整型 人员 0 在时刻 0 第一个接触到的人员编号
     * @return int整型ArrayList
     */
    public ArrayList<Integer> findAllPerson(int n, int[][] meetings, int firstPerson) {
        //按照时刻从小到大排序
        Arrays.sort(meetings, (o1, o2) -> o1[2] - o2[2]);
        System.out.println(Arrays.deepToString(meetings));
        HashSet<Integer> people = new HashSet<>();

        HashSet<Integer> grz = new HashSet<>();
        grz.add(firstPerson);

        for (int i = 0; i < meetings.length; i++) {
            int[] meeting = meetings[i];
            int timeNow = meeting[2];
            people.add(meeting[0]);
            people.add(meeting[1]);
            for (int j = i + 1; j < meeting.length; j++) {
                int[] nextMeeting = meetings[j];
                if (nextMeeting[2] == timeNow) {
                    people.add(nextMeeting[0]);
                    people.add(nextMeeting[1]);
                }
                //如果不一样就没必要往下走了。
                else {
                    break;
                }
            }
            //处理完同一时刻的所有people以后，查看people里面有没有firstPerson
            //如果没有，就全部清除
            //有就保留
            int flag = 0;
            for (Integer temp : grz) {
                if (people.contains(temp)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                //找出所有感染者，添加到结果中
                grz.addAll(people);
            }
            //不管怎么样当前people都要清空。
            people.clear();
        }

        //最后给grz排序即可
        ArrayList<Integer> res = new ArrayList<>(grz);
        Collections.sort(res);
        return res;
    }
}
