package 校招笔试真题.恒生电子;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/10/13
 */
public class 证券行情快照CSV数据文本解析分钟聚合计算K线 {

    static List<HQStockSnapshot> datas = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while (sc.hasNext()) {
            String str = sc.nextLine();
            if (str.startsWith("finance")) continue;
            HQStockSnapshot hqStockSnapshot = parseLine(str);
            datas.add(hqStockSnapshot);
        }
        klineAggr1Minute(datas);
    }

    public static HQStockSnapshot parseLine(String Line) {
        String[] ss = Line.split(",");
        int data_timestamp = Integer.parseInt(ss[3]);
        double open_px = Double.parseDouble(ss[5]);
        double high_px = Double.parseDouble(ss[7]);
        double low_px = Double.parseDouble(ss[8]);
        double close_px = Double.parseDouble(ss[9]);
        int business_count = Integer.parseInt(ss[10]);
        int business_amount = Integer.parseInt(ss[11]);
        double business_balance = Double.parseDouble(ss[12]);
        return new HQStockSnapshot(
                data_timestamp,
                open_px,
                high_px,
                low_px,
                close_px,
                business_count,
                business_amount,
                business_balance);
    }

    public static void klineAggr1Minute(List<HQStockSnapshot> datas) {

    }

}

class HQStockSnapshot {
    public int data_timestamp;
    public double open_px;
    public double high_px;
    public double low_px;
    public double close_px;
    public int business_count;
    public int business_amount;
    public double business_balance;

    public HQStockSnapshot(int data_timestamp,
                           double open_px, double high_px, double low_px, double close_px,
                           int business_count, int business_amount, double business_balance) {
        this.data_timestamp = data_timestamp;
        this.open_px = open_px;
        this.high_px = high_px;
        this.low_px = low_px;
        this.close_px = close_px;
        this.business_count = business_count;
        this.business_amount = business_amount;
        this.business_balance = business_balance;
    }
}
