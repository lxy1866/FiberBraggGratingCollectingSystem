package top.kaluna.modbusTcp.test;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Yuery
 * @date 2022/2/23/0023 - 16:09
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        Date date = new Date();

        System.out.println(date.getTime());
    }
}
