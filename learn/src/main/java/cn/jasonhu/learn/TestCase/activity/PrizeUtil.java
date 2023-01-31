package cn.jasonhu.learn.TestCase.activity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PrizeUtil {

    /**
     * 根据Math.random()产生一个double型的随机数，判断每个奖品出现的概率
     *
     * @param prizes
     * @return random：奖品列表prizes中的序列（prizes中的第random个就是抽中的奖品）
     */
    public int getPrizeIndex(List<Prize> prizes) {
        int random = -1;
        try {
            // 计算总权重
            double sumWeight = 0;
            for (Prize p : prizes) {
                sumWeight += p.getWeight();
            }

            // 产生随机数
            double randomNumber;
            // randomNumber = Math.random();
            randomNumber = ThreadLocalRandom.current().nextDouble();
            System.out.println("randomNumber : " + randomNumber);

            // 根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            int size = prizes.size();
            for (int i = 0; i < size; i++) {
                d2 += Double.parseDouble(String.valueOf(prizes.get(i).getWeight())) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(prizes.get(i - 1).getWeight()))
                            / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber < d2) {
                    random = i;
                    break;
                }
                System.out.println("d1 && d2=" + d1 + ", " + d2);
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        return random;
    }
}
