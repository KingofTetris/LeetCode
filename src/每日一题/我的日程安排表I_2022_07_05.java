package 每日一题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author KingofTetris
 * @File 我的日程安排表I_2022_07_05
 * @Time 2022/7/05 14:46
 *
 * 处理区间，类似的还有 715. Range 模块、2276. 统计区间中的整数数目
 *
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 *
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 *
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 *
 * 实现 MyCalendar 类：
 *
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。
 * 否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 * 示例：
 *
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]  注意 0 <= start < end <= 10^9
 * 输出：
 * [null, true, false, true]
 *
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True 左闭右开
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 *  
 *
 * 提示：
 *
 * 0 <= start < end <= 10^9
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 我的日程安排表I_2022_07_05 {
    @Test
    public void test() {
        /**
         ["MyCalendar","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book"]
         [[],[23,32],[42,50],[6,14],[0,7],[21,30],[26,31],[46,50],[28,36],[0,6],[27,36],[6,11],[20,25],[32,37],[14,20],[7,16],[13,22],[39,47],[37,46],[42,50],[9,17],[49,50],[31,37],[43,49],[2,10],[3,12],[8,14],[14,21],[42,47],[43,49],[36,43]]
         */
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
    }

/*    class MyCalendar {
        int[] day;
        public MyCalendar() {
            day = new int[99999]; //淦，一下午白做。因为对内存有限制，0 <= start < end <= 10^9 不可能拿一个数组把所以数放进去。
            //超出内存限制
            for (int i = 0; i < day.length; i++) {
                day[i] = i;
            }
        }

        public boolean book(int start, int end) {
            for (int i = start;i < end;i++){ //左闭右开
                if (day[i] == i){
                    day[i] = -1;//如果相减等于0，就用-1占用掉这一天 你要先确保这个行程一定会生效才真的让day[i]=0
                }
                else if (day[i] == -1){ //如果等于 -1 那这天已经被占用。
                    if (i == start){
                        //如果i是第一个区间的第一个数 就没必要改回j了。直接return false
                        return false;
                    }
                    //如果不是头一天，才需要从-1换回来。
                    for (int j = start;j < i;j++){ //发现20为21了，那么要把前面的13-19的数给他还回来。而不是用-1占着不放。
                        day[j] = j; //但问题就是如果是0
                    }
                    return false; //最后再return false
                }
            }
            return true;//如果遍历完都OK，那就可以返回true
        }
    }*/
class MyCalendar {
    TreeMap<Integer,Integer> map; //直接拿TreeMap判断两个相邻的区间有没有交集即可。
    public MyCalendar() {
        map=new TreeMap<>();
        map.put(-1,-1);
        map.put((int)1e9+1,(int)1e9+1);
    }

    public boolean book(int start, int end) {
        Integer a=map.ceilingKey(start);//右边
        Integer b=map.floorKey(start);//左边
        if(a<end||map.get(b)>start){return false;}
        map.put(start,end);
        return true;
    }
}

class MyCalendar2 { //暴力法用列表
        List<int[]> list; //建立一个装着区间的空列表 [ ]
        public MyCalendar2() {
            list=new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for(int i=0;i<list.size();i++){
                int a[]=list.get(i); //取出每个区间a[]
                //
                if(!(end-1<a[0]||start>a[1])) {return false;} //如果有交集 有交集的情况比较多，我们反过来想没有交集的情况
                // 也就是 end-1<a[0]||start>a[1]
                // 那就返回false
            }
            list.add(new int[]{start,end-1});//放入区间 new int{} 主义是花括号

            //全部输入区间放入list都没有碰到交集就返回true
            return true;
        }
    }

    /**
    * Your MyCalendar object will be instantiated and called as such:
    * MyCalendar obj = new MyCalendar();
    * boolean param_1 = obj.book(start,end);
    */
}
