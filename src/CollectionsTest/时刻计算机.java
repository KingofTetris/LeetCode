package CollectionsTest;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/27 11:30
 */
public class 时刻计算机 {

    @Test
    public void test(){
        change("09:44","14:45");
    }

    public void change(String s1,String s2){
        String[] split1 = s1.split(":");
        String[] split2 = s2.split(":");
        int aH = Integer.parseInt(split1[0]);
        int aM = Integer.parseInt(split1[1]);
        int bH = Integer.parseInt(split2[0]);
        int bM = Integer.parseInt(split2[1]);

        int aTotal = aH * 60 + aM;
        int bTotal = bH * 60 + bM;

        int dayTotal = bTotal - aTotal;

        int hour = dayTotal / 60;
        int minute = dayTotal % 60 ;
        double minuteH = (dayTotal % 60);
        double hourS = hour + minuteH / 60;
        System.out.println(hourS);
    }
}
