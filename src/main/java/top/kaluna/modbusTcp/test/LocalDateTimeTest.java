package top.kaluna.modbusTcp.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Yuery
 * @date 2022/2/23/0023 - 16:09
 */
public class LocalDateTimeTest {
    public static void main(String[] args) throws ParseException {
        System.out.println(LocalDate.now());
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println("------getStartTime-------");
        System.out.println(getStartTime());
        System.out.println(getStartTime().getTime());

        System.out.println("------getNowTime-------");
        System.out.println(getNowTime());
        System.out.println(getNowTime().getTime());
        System.out.println("------getEndTime-------");
        System.out.println(getEndTime());
        System.out.println(getEndTime().getTime());
//        String patterStr = "yyyy-MM-dd HH:mm:ss";
//
//        // 模拟请求参数的时间字符串
//        String dateStrParam = "2020-01-15 18:00:00";
//
//        // 模拟服务端对此服务换转换为Date类型
//        DateFormat dateFormat = new SimpleDateFormat(patterStr);
//        System.out.println("格式化器用的时区是：" + dateFormat.getTimeZone().getID());
//        Date date = dateFormat.parse(dateStrParam);
//        System.out.println(date);
    }
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(new Date());
        todayStart.set(Calendar.HOUR, -12);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(new Date());
        todayEnd.set(Calendar.HOUR, 11);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }
    public static Date getNowTime(){
        Calendar todayStart = Calendar.getInstance();
        return todayStart.getTime();
        //return new Date();
    }
}
