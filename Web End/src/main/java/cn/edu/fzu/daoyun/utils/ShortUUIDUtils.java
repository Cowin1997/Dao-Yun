package cn.edu.fzu.daoyun.utils;

import java.util.Random;
import java.util.UUID;

public class ShortUUIDUtils {
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static Integer generateShortUuid() {
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
//随机生成数字，并添加到字符串
        for(int i=0;i<8;i++){
            str.append(random.nextInt(10));
        }
//将字符串转换为数字并输出
        int num=Integer.parseInt(str.toString());
        return num;
    }
}
