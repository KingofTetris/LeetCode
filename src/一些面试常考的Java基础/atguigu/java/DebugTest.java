package 一些面试常考的Java基础.atguigu.java;

/*
  调试是因为程序没有报错，但是给出了错误的结果。程序正常运行结果不对所以要调试看过程。
  1.传统的syso。走一步看一步 适合小程序
  2.Debug调试 step over相当于往下执行，step into相当于两块代码之间，下面还有断点resume就执行下面的断点，没有就相当于停止。
 */
public class DebugTest {
    /*public static void main(String[] args){
        int i = 10;
        int j = 20;
        System.out.println("i = " + i + ", j =" + j);

        DebugTest test = new DebugTest();
        int max = test.getMax(i,j);
        System.out.println("max = " + max);
    }

    private int getMax(int i, int j) {
        int max = 0;
        if(i < j){
            max = i;
        }else{
            max = j;
        }
        return max;
    }
*/
}
