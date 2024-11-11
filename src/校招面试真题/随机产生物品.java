package 校招面试真题;

import java.util.Arrays;
import java.util.Random;

public class 随机产生物品 {

    public static void main(String[] args) {
        String[] items = {"黄花鱼", "鲈鱼", "鲢鱼", "大闸蟹", "神秘宝箱"};
        int[] probabilities = {28, 26, 26, 10, 10};

        int[] distribution = createDistribution(probabilities);
        System.out.println(Arrays.toString(distribution));
        int t = 10;
        while (t-- > 0){
            int randomIndex = getRandomIndex();
            String result = getItemFromDistribution(distribution, randomIndex, items);
            System.out.println("随机结果：" + result);
        }
    }

    // 创建随机分布数组
    public static int[] createDistribution(int[] probabilities) {
        int[] distribution = new int[100];
        int index = 0;

        for (int i = 0; i < probabilities.length; i++) {
            for (int j = index; j < index + probabilities[i]; j++) {
                distribution[j] = i;
            }
            index += probabilities[i];
        }

        return distribution;
    }

    // 生成一个0-99的随机数
    public static int getRandomIndex() {
        Random random = new Random();
        return random.nextInt(0,100);
    }

    // 根据随机数获取对应物品
    public static String getItemFromDistribution(int[] distribution, int randomIndex, String[] items) {
        return items[distribution[randomIndex]];
    }
}
