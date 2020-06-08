package activity;

import java.util.ArrayList;
import java.util.List;

public class TestPrize {

    public static void main(String[] args) {
        int i = 0;
        PrizeUtil a = new PrizeUtil();
        int[] result = new int[5];
        List<Prize> prizes = new ArrayList<Prize>();

        Prize p1 = new Prize();
        p1.setName("范冰冰海报");
        p1.setWeight(1);// 奖品的权重设置成1
        prizes.add(p1);

        Prize p2 = new Prize();
        p2.setName("上海紫园1号别墅");
        p2.setWeight(1);// 奖品的权重设置成1
        prizes.add(p2);

        Prize p3 = new Prize();
        p3.setName("奥迪a9");
        p3.setWeight(1);// 奖品的权重设置成1
        prizes.add(p3);

        Prize p4 = new Prize();
        p4.setName("双色球彩票");
        p4.setWeight(1);// 奖品的权重设置成1
        prizes.add(p4);

        Prize p5 = new Prize();
        p5.setName("未中奖");
        p5.setWeight(1);// 奖品的权重设置成1
        prizes.add(p5);

        System.out.println("抽奖开始");
        for (i = 0; i < 10000; i++) {// 打印100个测试概率的准确性
            int selected = a.getPrizeIndex(prizes);
            System.out.println("第" + i + "次抽中的奖品为：" + prizes.get(selected).getName());
            result[selected]++;
            System.out.println("--------------------------------");
        }

        System.out.println("抽奖结束");
        System.out.println("每种奖品抽到的数量为：");
        System.out.println("一等奖：" + result[0]);
        System.out.println("二等奖：" + result[1]);
        System.out.println("三等奖：" + result[2]);
        System.out.println("四等奖：" + result[3]);
        System.out.println("五等奖：" + result[4]);
    }
}
