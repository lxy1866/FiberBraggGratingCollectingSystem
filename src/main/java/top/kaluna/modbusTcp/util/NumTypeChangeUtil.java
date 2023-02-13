package top.kaluna.modbusTcp.util;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Yuery
 * @date 2021/12/12/0012 - 16:51
 */
public class NumTypeChangeUtil {
    /**
     *
     * @param src byte数组
     * @param offset 从数组的offset位开始
     * @return int 数值
     */
    public static int bytesToInt2(byte[] src, int offset){
        int value;
        value = (int) (((src[offset] & 0xFF) << 24)
                | ((src[offset+1] & 0xFF) << 16)
                | ((src[offset+2] & 0xFF) << 8)
                | ((src[offset+3] & 0xFF)));
        return value;
    }
    public static float bytesToFloat(byte[] src, int offset){
        int value;
        value = (int) (((src[offset] & 0xFF) << 24)
                | ((src[offset+1] & 0xFF) << 16)
                | ((src[offset+2] & 0xFF) << 8)
                | ((src[offset+3] & 0xFF)));

        return Float.intBitsToFloat(value);
    }
    //低字节在前
    public static float bytesToFloat2(byte[] src, int offset){
        int value;
        value = (int) (((src[offset] & 0xFF)
                | ((src[offset+1] & 0xFF) << 8)
                | ((src[offset+2] & 0xFF) << 16)
                | ((src[offset+3] & 0xFF) << 24)));

        return Float.intBitsToFloat(value);
    }

    //两字节
    public static float bytesToFloat3(byte[] src, int offset){
        String tmp1;
        String tmp2;

        tmp1 = Integer.toHexString(0xFF & src[offset+1]);
        if (tmp1.length() == 1) {
            tmp1 = "0" + tmp1;//只有一位的前面补个0
        }
        tmp2 = Integer.toHexString(0xFF & src[offset]);
        if (tmp2.length() == 1) {
            tmp2 = "0" + tmp2;//只有一位的前面补个0
        }

        float result=(float) Integer.parseInt(tmp1+tmp2,16);

        return result;
    }

//    static public BigDecimal bytesToBigDecimal(byte[] buffer) {
//        String string = bytesToString(buffer);
//        return new BigDecimal(string);
//    }
}
