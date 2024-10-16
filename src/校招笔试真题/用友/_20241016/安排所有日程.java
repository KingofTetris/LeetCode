package 校招笔试真题.用友._20241016;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/16
 */

//要么true 要么false，当然50%，TODO 这题等于没做出来
public class 安排所有日程 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //会议室大小
        int[] roomsSize = new int[n];
        //会议具体情况
        int[][] meetings = new int[m][3];
        for (int i = 0; i < n; i++) {
            roomsSize[i] = sc.nextInt();
        }
        //m个会议
        //[startTime,endTime,num]
        //表示 [会议的开始时间,会议的结束时间,参会人数]
        //注意会议结束时间是开区间。
        for (int i = 0; i < m; i++) {
            meetings[i][0] = sc.nextInt();
            meetings[i][1] = sc.nextInt();
            meetings[i][2] = sc.nextInt();
        }
        //请问这n间会议室，能否安排完所有的会议。
        //如果可以返回true，否则返回false。
        Integer[] roomsSizeDup = new Integer[roomsSize.length];
        for (int i = 0; i < roomsSize.length; i++) {
            roomsSizeDup[i] = roomsSize[i];
        }
        boolean res = canScheduleMeetings(roomsSizeDup, meetings);
        System.out.println(res);
    }

    //微盟，微办，万里牛
    public static boolean canScheduleMeetings(Integer[] roomsSize, int[][] meetings) {
        //按照房间大小从小到大排序
        Arrays.sort(roomsSize,Comparator.reverseOrder());
        // 按会议开始时间从小到大排序
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        // 按照结束时间建立最小堆。
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < meetings.length; i++) {
            int[] meeting = meetings[i];
            int num = meeting[2];//会议人数
            // 检查是否有可用会议室
            boolean foundRoom = false;
            // 选择哪一个会议室，从小到大去找可以容纳的会议室。
            for (int j = 0; j < roomsSize.length; j++) {
                if (roomsSize[j] >= num) {
                    roomsSize[j] -= num;
                    foundRoom = true;
                    break;
                }
            }
            //如果没找到 可以容纳的会议室
            if (!foundRoom) {
                // 从优先队列中删去最早开始的会议，回复roomSize
                int[] earliestMeeting = pq.poll();
                int earliestEndTime = earliestMeeting[1];
                int earliestNum = earliestMeeting[2];
                int nowStartTime = meeting[0];
                //同一个会议室应该是不能举办多个会议的。
                //那么如果前一个会议的结束时间，比当前会议的开始时间还要后面
                //那么自然就找不到可以安排的会议室了。
                if (earliestEndTime > nowStartTime){
                    return false;
                }
                // 检查这个会议室能否用于现在的会议。
                boolean canReuseRoom = false;
                for (int j = 0; j < roomsSize.length; j++) {
                    if (roomsSize[j] + earliestNum >= num) {
                        roomsSize[j] += earliestNum - num;
                        canReuseRoom = true;
                        break;
                    }
                }
                if (!canReuseRoom) {
                    // 无法安排会议
                    return false;
                }
            }
            // 当前会议加入优先队列
            pq.offer(meeting);
        }
        // 可以安排
        return true;
    }

}
