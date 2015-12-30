package com.red.common.algorithm;

import com.red.domain.OrgRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * Created by huichao on 2015/12/24.
 */
public class RedGenerateUtil {

    private static float[] RED_SCALE = {0.3f, 0.4f};
    private static Logger logger = LogManager.getLogger(RedGenerateUtil.class);


    public static int[] generate(OrgRule orgRule) {
        float scale = 1;

        int moneySum = orgRule.getAveragePrice()*orgRule.getRedCount();
//        if (null != orgRule.getCost() && orgRule.getCost() > 0) {
//            moneySum += orgRule.getCost();
//        }

        if (orgRule.getType()) {
            scale = RED_SCALE[orgRule.getRedCount()%2];
            logger.info("Scale ---> "+scale);

            return resultReturn(scale, orgRule.getAveragePrice(), moneySum, orgRule.getRedCount());
        }else{
            logger.info("Scale ---> "+scale);
            int[] result = new int[orgRule.getRedCount().intValue()];
            for(int i=0;i<orgRule.getRedCount().intValue();i++){
                result[i]=orgRule.getAveragePrice().intValue();
            }

            return  result;
        }

    }

    /**
     * Generate reds int [ ].
     *
     * @param price  the price
     * @param amount the amount
     * @return the int [ ]
     */
    public static int[] generate(int price, int amount) {
        float scale = RED_SCALE[amount%2];
        logger.info("Scale ---> "+scale);
        return resultReturn(scale, price, price*amount, amount);
    }


    /**
     * 金额的精度：保留两位小数
     */
    private static int scale = 2;

    /**
     * 给一个total 钱分给numberOfPeople 人，每个人分的红包不能少于平均数的五分之一，也是2/8分发
     */
    public static int[] resultReturn(double scale,int price,int total,int numberOfPeople){
        int[] result = new int[numberOfPeople];
        BigDecimal[] mid= null;
        int minMoney = 1;//1分
        if(scale*price>1){
            minMoney=(int)(scale*price);
        }
        logger.info("min money ---> "+minMoney);

        //剩余的钱保证每个人大于-分，否则，按minMoney=1分按保底值
        int tmpMoney=total-minMoney*numberOfPeople;
        if(tmpMoney==0){
            for(int i=0;i<numberOfPeople;i++){
                result[i]=minMoney;
            }
        }else{
            //剩余的钱是否够每人一分钱
            if(numberOfPeople>tmpMoney){
                for(int i=0;i<numberOfPeople;i++){
                    result[i]= minMoney;
                }
                for(int i=0;i<tmpMoney;i++){
                    result[i]= result[i]+1;
                }
            }else{
                mid=generalPlay(new BigDecimal((double)(total-minMoney*numberOfPeople)/100),numberOfPeople);
                for(int i=0;i<mid.length;i++){
                    result[i]=minMoney+(int)(mid[i].doubleValue()*100);
                }
            }
        }
        //保证总金额相等
        int tmp=0;
        for(int i=0;i<numberOfPeople-1;i++){
           tmp=tmp+ result[i];
        }
        result[numberOfPeople-1]=total-tmp;

        return result;
    }
    /**
     * 普通玩法
     * <p>
     * 输入金额、人数，随机输出金额列表，例如：<br>
     * 输入：1.0 3<br>
     * 输出：0.11 0.37 0.52
     * </p>
     *
     * @return
     */
    public static BigDecimal[] generalPlay(final BigDecimal money, int numberOfPeople) {

        // 将金额转化为分，就能转化为整数
        BigDecimal divisor = new BigDecimal(100);
        int n = money.multiply(divisor).intValue();
        // 从1--n之间随机抽出numberOfPeople个数。其实这里就是一个抽样问题
        BigDecimal[] result = new BigDecimal[numberOfPeople];
        int m = numberOfPeople;
        int index = 0;
        for (int i = 0; i < n; i++) {
            long bigrand = bigRand();
            if (bigrand % (n - i) < m) {
                result[index++] = new BigDecimal(i + 1).divide(divisor, scale,
                        BigDecimal.ROUND_HALF_UP);
                m--;
            }
        }
        // 分区间处理
        for (int i = numberOfPeople - 1; i > 0; i--) {
            if (i == (numberOfPeople - 1)) {
                // 最后一个就取剩余的
                result[i] = money.subtract(result[i - 1]);
            } else {
                result[i] = result[i].subtract(result[i - 1]);
            }
        }
        return result;
    }

    /**
     * 产生一个很大的随机整数
     *
     * @return
     */
    private static long bigRand() {
        long bigrand = (long) (Math.random() * Integer.MAX_VALUE) + Integer.MAX_VALUE;

        return bigrand;
    }


    public static void main(String []args){
        //int [] result=resultReturn(0.1,2,100,92);
        int [] result=generate(10, 5);
        int total=0;
        for(int rs:result){
            total=total+rs;
            System.out.println(rs);
        }

        System.out.println("---"+total);
    }
}
