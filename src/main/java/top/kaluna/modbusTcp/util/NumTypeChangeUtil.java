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

//    static public BigDecimal bytesToBigDecimal(byte[] buffer) {
//        String string = bytesToString(buffer);
//        return new BigDecimal(string);
//    }
}
