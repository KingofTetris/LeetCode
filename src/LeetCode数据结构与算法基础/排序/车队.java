package LeetCode数据结构与算法基础.排序;

import java.util.Arrays;


class Car {
    int position;
    double time;
    Car(int p, double t) {
        position = p;
        time = t;
    }
}

public class 车队 {
    public int carFleet(int target, int[] position, int[] speed) {
        int N = position.length;
        Car[] cars = new Car[N];
        for (int i = 0; i < N; ++i)
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        Arrays.sort(cars, (a, b) -> a.position - b.position);

        int ans = 0, t = N;
        while (--t > 0) {
            if (cars[t].time < cars[t-1].time) ans++; //if cars[t] arrives sooner, it can't be caught
            else cars[t-1] = cars[t]; //else, cars[t-1] arrives at same time as cars[t]
        }

        return ans + (t == 0 ? 1 : 0); //lone car is fleet (if it exists)
    }
}



/*作者：LeetCode
链接：https://leetcode.cn/problems/car-fleet/solutions/20104/che-dui-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
