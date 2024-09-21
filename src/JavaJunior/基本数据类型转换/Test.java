package JavaJunior.基本数据类型转换;

/**
 * @author by KingOfTetris
 * @date 2024/9/21
 */
public class Test {

    public static void main(String[] args) {
        int a = 10;
        double b = a / 4;//没有隐式转换发生??不是没有，是发生了，但是是把2转换成了2.0
        //如果你要得到2.5，那你一开始就要当作浮点数除法
        double c = (double) a / 4;
        System.out.println(b);
        System.out.println(c);
    }
}
