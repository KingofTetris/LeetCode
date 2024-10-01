package LeetCode数据结构与算法基础.线段树;

import org.junit.Test;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2022/7/19 10:08
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 *
 * MyCalendar 有一个 book(int start, int end)方法。
 * 它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，
 * 即 [start, end), 实数 x 的范围为，  start <= x < end。
 *
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 *
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，
 *
 * 返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 *  
 *
 * 示例：
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * 解释：
 * 前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
 * 第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
 * 第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
 * 第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
 * 时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
 *  
 *
 * 提示：
 *
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。
 * 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 我的日程安排表II_2022_07_19 {
    @Test
    public void test(){
        MyCalendarTwo MyCalendar = new MyCalendarTwo();
        int[][] list = {{24,40},{43,50},{27,43},{5,21},{30,40},{14,29},{3,19},{3,14},{25,39},{6,19}};

        for (int[] ints : list) {
           MyCalendar.book(ints[0], ints[1]);
        }
        for (Map.Entry<String, Integer> integerEntry : MyCalendar.areaDupilicate.entrySet()) {
//            String key = integerEntry.getKey();
            // 卧槽？区间的地址是不一样的，我操！ 所以即使内容一样也是不同的key!。
//            System.out.println( "地址:" + key + "\t" + "[" + key[0] + "," + key[1] + "]"
//                    + "\t" + integerEntry.getValue());
            System.out.println(integerEntry.getKey() + "\t" + integerEntry.getValue());
        }
    }
}

/**
 * I是只要发生重叠就不行。
 * II是只能发生2次重叠
 */
class MyCalendarTwo {

    /**
     * 用Map存区间和重叠次数 区间要存成String，int[]数组 即使内容相同，地址也不同 无法达到效果
     */
    LinkedList<int[]> calendar;
//    Map<int[],Integer> areaDupilicate;
    Map<String,Integer> areaDupilicate;//这个区间重复了几次

    Map<String,Integer> areaCount;//这个区间加了几次
    public MyCalendarTwo() {
        calendar = new LinkedList<>();
        areaDupilicate = new HashMap<>();
        areaCount = new HashMap<>();
    }

    /**
     * 那就要和前面的每一个都比，不能只比较相邻的
     * @param start
     * @param end
     * @return
     */
    public boolean book(int start, int end) {
        int[] a = new int[]{start,end-1};

        /**
         * 遍历列表
         */
        for (int i = 0; i < calendar.size(); i++) {
            int[] b = calendar.get(i);
            //存在交集先求出这个交集
            if (!(start > b[1] || end - 1 < b[0])){
                int[] inter = intersection(a, b);
                String key = inter[0] + "," + inter[1];
                areaDupilicate.put(key,
                        areaDupilicate.getOrDefault(key,0) + 1);//否则把区间放进来+1

                areaCount.put(key,
                        areaCount.getOrDefault(key,0) + 1);//计算这个区间加了几次

                if (areaDupilicate.get(key) == 2) {
                    areaDupilicate.put(key,areaDupilicate.get(key) - areaCount.get(key));//当前的值减去累加的
                    areaCount.put(key,0);//没添加成功，不保留次数，重新置为0次
                    return false;//加了以后等于2 即区间重叠了2次，也就是三重预定了
                    //而且应该把map返回未累加的状态
                }
            }
        }
        /**
         * 上面存在问题就是如果这个区间最后没有加进去，但是你已经给他的区间加过一次了。
         */
        //添加成功
        calendar.add(a);
        return true;
    }

    /**
     * 这个函数保证了存在交集
     * 并且如果存在交集[c1,c2]的话
     * c1 = max{a[0],b[0]}
     * c2 = min{a[1],b[1]}
     * @param a
     * @param b
     * @return
     */
    public int[] intersection(int[] a,int[] b){
       int c1 = Math.max(a[0],b[0]);
       int c2 = Math.min(a[1],b[1]);
       return new int[]{c1,c2};
    }
}


/**
 * 记录下所有已经预定的课程安排区间与已经预定过两次的课程安排区间，
 * 当我们预定新的区间[start,end) 时，
 * 此时检查当前已经预定过两次的每个日程安排是否与新日程安排冲突。
 * 若不冲突，则可以添加新的日程安排。
 * O(n^2)
 */
class MyCalendarTwo2 {
    List<int[]> booked;
    List<int[]> overlaps;

    public MyCalendarTwo2() {
        booked = new ArrayList<int[]>();
        overlaps = new ArrayList<int[]>();
    }

    public boolean book(int start, int end) {

        /**
         * overlaps就是预定的区间 如果新加入的区间和已经预定两次的区间存在交集，那就return false
         */
        for (int[] arr : overlaps) {
            int l = arr[0], r = arr[1];
            if (l < end && start < r) { //这个就是有交集的条件和 !( start > right || end - 1 < left)一样
                return false;
            }
        }
        /**
         * 如果没有冲突，则将新加入的区间与已经预定的区间进行检查，求出新增的预定两次的区间。
         * 两区间的交集 {更大的左端点,更小的右端点}
         */
        for (int[] arr : booked) {
            int l = arr[0], r = arr[1];
            if (l < end && start < r) {
                overlaps.add(new int[]{Math.max(l, start), Math.min(r, end)}); //booked一次，arr又一次就是两次
            }
        }
        booked.add(new int[]{start, end});
        return true;
    }
    //就这么简单。你搞那么复杂干什么？
}


/**
 如果给你一个包含5000万个元素的数组，然后会有频繁区间修改操作，
 那什么是频繁的区间修改操作呢？比如让第1个数到第1000万个数每个数都加上1，而且这种操作时频繁的。

 此时你应该怎么做？很容易想到的是，从第1个数开始遍历，一直遍历到第1000万个数，
 然后每个数都加上1，如果这种操作很频繁的话，那这种暴力的方法在一些实时的系统中可能就拉跨了。

 因此，今天的主角就出现了——差分数组。

 差分数组是前缀和的逆运算，求出差分数组，再求一遍前缀和，就相当于对元素组进行了操作。
 *
 */
class MyCalendarTwo3 {
    TreeMap<Integer, Integer> cnt;
    public MyCalendarTwo3() {
        cnt = new TreeMap<Integer, Integer>(); //由于本题中start,end 数量较大，我们利用 TreeMap 计数即可。
    }
    public boolean book(int start, int end) {
        int ans = 0;
        int maxBook = 0;
        cnt.put(start, cnt.getOrDefault(start, 0) + 1);
        cnt.put(end, cnt.getOrDefault(end, 0) - 1);
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int freq = entry.getValue();
            maxBook += freq;
            ans = Math.max(maxBook, ans);
            if (maxBook > 2) {
                cnt.put(start, cnt.getOrDefault(start, 0) - 1);
                cnt.put(end, cnt.getOrDefault(end, 0) + 1);
                return false;
            }
        }
        return true;
    }
}
