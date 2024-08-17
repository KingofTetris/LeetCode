package 校招笔试真题.华为OD.A卷;

/**
 * @author by KingOfTetris
 * @date 2024/8/16
 */
public class 找座位 {

    public static int maxAdditionalSeats(int[] seats) {
        int count = 0; // 新观众的计数
        int n = seats.length;

        for (int i = 0; i < n; i++) {
            // 如果当前座位是空的
            if (seats[i] == 0) {
                // 检查左边是否空（或左边界）
                boolean leftEmpty = (i == 0) || (seats[i - 1] == 0);
                // 检查右边是否空（或右边界）
                boolean rightEmpty = (i == n - 1) || (seats[i + 1] == 0);

                // 如果左右都是空的，当前座位可以坐人
                if (leftEmpty && rightEmpty) {
                    count++; // 新增观众计数
                    i++; // 跳过下一个座位，因为不能相邻坐人
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] seats1 = {1, 0, 0, 0, 1, 0, 1};
        System.out.println(maxAdditionalSeats(seats1)); // 输出应为 1

        int[] seats2 = {0, 0, 0, 0, 0};
        System.out.println(maxAdditionalSeats(seats2)); // 输出应为 3

        int[] seats3 = {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1};
        System.out.println(maxAdditionalSeats(seats3)); // 输出应为 3

        int[] seats4 = {1, 0, 0, 0, 0, 1};
        System.out.println(maxAdditionalSeats(seats4)); // 输出应为 1

        int[] seats5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
        System.out.println(maxAdditionalSeats(seats5)); // 输出应为 3

        int[] seats6 = {1,0,0,0,1};
        System.out.println(maxAdditionalSeats(seats6)); // 输出应为 1
    }
}




