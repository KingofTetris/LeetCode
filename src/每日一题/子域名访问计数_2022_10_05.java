package 每日一题;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author KingofTetris
 * @Date 2022/10/5 9:26
 * https://leetcode.cn/problems/subdomain-visit-count/
 */
public class 子域名访问计数_2022_10_05 {

    @Test
    public void test(){
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> list = subdomainVisits(cpdomains);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> lists = new LinkedList<>();
        if (cpdomains.length == 0) return lists;
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < cpdomains.length; i++) {

            //先把前面的数字取出来
            String[] s = cpdomains[i].split(" ");
            int num = Integer.parseInt(s[0]);

            //再取出后面的域名
            String domain = s[1];
            //一个知识点在使用"."进行分割时，需要使用双斜杠进行转义，即split("\\.")
            // 如果是一个. 那split会认为是正则表达式里的任意字符
            // 所以要加上转义字符\\ 来表示为普通字符
            String[] split = domain.split("\\.");//为了取得最后一个子域名

            while (domain.contains(".") || domain.equals(split[split.length - 1])){ //如果包含.或者等于最后一个子域名
                if (domain.equals(split[split.length - 1])){ //如果是后一个条件成立
                    map.put(domain,map.getOrDefault(domain,0) + num);//只需要记录次数，不用再去截子串
                    break;
                }
                map.put(domain,map.getOrDefault(domain,0) + num);//记录次数
                int i1 = domain.indexOf(".");//找到从左往右第一个.的位置
                domain = domain.substring(i1 + 1);//把之前的字符舍去
            }
        }


        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            StringBuffer sb = new StringBuffer();
             sb.append(stringIntegerEntry.getValue());
             sb.append(" ");
             sb.append(stringIntegerEntry.getKey());
             lists.add(sb.toString());
        }
        return lists;
    }
}
