package top.kaluna.modbusTcp.test;
import top.kaluna.modbusTcp.util.DateUtil;
import java.text.ParseException;

/**
 * @author Yuery
 * @date 2022/2/23/0023 - 16:09
 */
public class LocalDateTimeTest {
    public static void main(String[] args) throws ParseException {


        System.out.println("------getCurrYearFirst-------");
        System.out.println(DateUtil.getCurrYearFirst());
        System.out.println(DateUtil.getCurrYearFirst().getTime());
        System.out.println("------getCurrYearLast-------");
        System.out.println(DateUtil.getCurrYearLast());
        System.out.println(DateUtil.getCurrYearLast().getTime());

        System.out.println("------Last24HoursStart-------");
        System.out.println(DateUtil.LastNHoursStart(24));
        System.out.println(DateUtil.LastNHoursStart(24).getTime());

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
}
