package LeetCode数据结构与算法基础.回溯;

import java.util.*;

class Product {
    int price;
    int stock;

    public Product(int price, int stock) {
        this.price = price;
        this.stock = stock;
    }
}
//给出N种商品的价格和库存，再给你预算P，请问P有多少种方案，可以正好花光？
public class PurchasePlans {
    public static void main(String[] args) {
        int totalBudget = 100; // 总预算
        Product[] products = {
            new Product(10, 5),  // 商品1：价格为10，库存为5
            new Product(20, 3),  // 商品2：价格为20，库存为3
            new Product(15, 4)   // 商品3：价格为15，库存为4
        };
        List<List<Integer>> purchasePlans = findPurchasePlans(totalBudget, products);
        // 输出所有购买方案
        for (List<Integer> plan : purchasePlans) {
            int sum = plan.stream().mapToInt(Integer::intValue).sum();
            System.out.println("购买方案: " + plan + "\t" + "剩余金额为:" + (totalBudget - sum));
        }
    }

    public static List<List<Integer>> findPurchasePlans(int totalBudget, Product[] products) {
        List<List<Integer>> purchasePlans = new ArrayList<>();
        List<Integer> currentPlan = new ArrayList<>();
        backTracking(0, totalBudget, products, currentPlan, purchasePlans);
        return purchasePlans;
    }

    public static void backTracking(int startIndex, int remainingBudget, Product[] products,
                                    List<Integer> currentPlan, List<List<Integer>> purchasePlans) {
        if (remainingBudget == 0) {
            purchasePlans.add(new ArrayList<>(currentPlan));
            return;
        }

        if (startIndex == products.length) {
            return;
        }

        //如果商品库存还够，并且剩余预算也够。
        for (int i = 0; i <= products[startIndex].stock
                && i * products[startIndex].price <= remainingBudget; i++) {
            //尝试添加当前物品。从0到i个
            for (int j = 0; j < i; j++) {
                currentPlan.add(products[startIndex].price);
            }

            backTracking(startIndex + 1,
                    remainingBudget - i * products[startIndex].price,
                    products, currentPlan, purchasePlans);

            //回溯删除这些物品
            for (int j = 0; j < i; j++) {
                currentPlan.remove(currentPlan.size() - 1);
            }
        }
    }
}
