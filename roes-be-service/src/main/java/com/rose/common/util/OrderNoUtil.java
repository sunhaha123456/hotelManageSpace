package com.rose.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNoUtil {

    public static String getNoHotel(Long id) {
        return getNO(0, id);
    }

    /**
     * 功能：生产订单编号
     * @param type
     * @param id 唯一字符串
     * @return
     */
    public static String getNO(Integer type, Long id) {
        String idStr = id + "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String dateStr = simpleDateFormat.format(new Date());
        int intNum=(int)(Math.random()*10000);
        String randNum = String.format("%04d", intNum);
        String idSuffixStr = null;
        String idZeroStr = "0000000";
        int diff = idZeroStr.length() - idStr.length();
        if (diff > 0) {
            idSuffixStr  = idZeroStr.substring(0, diff) + idStr;
        } else {
            idSuffixStr = idStr;
        }
        return new StringBuilder().append(dateStr).append(type).append(randNum).append(idSuffixStr).toString();
    }

//    public static void main(String[] args) {
//        for (int x = 0; x<1000 ;x++) {
//            int intNum=(int)(Math.random()*10000);
//            String r = getNO(intNum + "");
//            System.out.println(r);
//        }
//    }
}