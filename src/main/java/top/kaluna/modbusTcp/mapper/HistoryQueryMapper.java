package top.kaluna.modbusTcp.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface HistoryQueryMapper {

    @Select("select time from fbg_query where time between #{startTime} and #{endTime}")
    List<String> selectT(Date startTime, Date endTime);

    @Select("select tempeture1 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectT1(Date startTime, Date endTime);

    @Select("select tempeture2 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectT2(Date startTime, Date endTime);

    @Select("select tempeture3 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectT3(Date startTime, Date endTime);

    @Select("select vibration from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectV(Date startTime, Date endTime);

    @Select("select strain1 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS1(Date startTime, Date endTime);

    @Select("select strain2 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS2(Date startTime, Date endTime);

    @Select("select strain3 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS3(Date startTime, Date endTime);

    @Select("select strain4 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS4(Date startTime, Date endTime);

    @Select("select strain5 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS5(Date startTime, Date endTime);

    @Select("select strain6 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS6(Date startTime, Date endTime);

    @Select("select strain7 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS7(Date startTime, Date endTime);

    @Select("select strain8 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS8(Date startTime, Date endTime);

    @Select("select strain9 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS9(Date startTime, Date endTime);

    @Select("select strain10 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS10(Date startTime, Date endTime);

    @Select("select strain11 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS11(Date startTime, Date endTime);

    @Select("select strain12 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS12(Date startTime, Date endTime);

    @Select("select strain13 from fbg_query where time between #{startTime} and #{endTime}")
    List<Double> selectS13(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x25' and create_time between #{startTime} and #{endTime}")
    List<String> selectT25(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x26' and create_time between #{startTime} and #{endTime}")
    List<String> selectT26(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x27' and create_time between #{startTime} and #{endTime}")
    List<String> selectT27(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x28' and create_time between #{startTime} and #{endTime}")
    List<String> selectT28(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x29' and create_time between #{startTime} and #{endTime}")
    List<String> selectT29(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x30' and create_time between #{startTime} and #{endTime}")
    List<String> selectT30(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x31' and create_time between #{startTime} and #{endTime}")
    List<String> selectT31(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x32' and create_time between #{startTime} and #{endTime}")
    List<String> selectT32(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x33' and create_time between #{startTime} and #{endTime}")
    List<String> selectT33(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x34' and create_time between #{startTime} and #{endTime}")
    List<String> selectT34(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x35' and create_time between #{startTime} and #{endTime}")
    List<String> selectT35(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x36' and create_time between #{startTime} and #{endTime}")
    List<String> selectT36(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x37' and create_time between #{startTime} and #{endTime}")
    List<String> selectT37(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x38' and create_time between #{startTime} and #{endTime}")
    List<String> selectT38(Date startTime, Date endTime);

    @Select("select create_time from position_value where hexadecimal = '0x39' and create_time between #{startTime} and #{endTime}")
    List<String> selectT39(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x25' and create_time between #{startTime} and #{endTime}")
    List<Double> select25x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x25' and create_time between #{startTime} and #{endTime}")
    List<Double> select25y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x25' and create_time between #{startTime} and #{endTime}")
    List<Double> select25z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x26' and create_time between #{startTime} and #{endTime}")
    List<Double> select26x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x26' and create_time between #{startTime} and #{endTime}")
    List<Double> select26y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x26' and create_time between #{startTime} and #{endTime}")
    List<Double> select26z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x27' and create_time between #{startTime} and #{endTime}")
    List<Double> select27x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x27' and create_time between #{startTime} and #{endTime}")
    List<Double> select27y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x27' and create_time between #{startTime} and #{endTime}")
    List<Double> select27z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x28' and create_time between #{startTime} and #{endTime}")
    List<Double> select28x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x28' and create_time between #{startTime} and #{endTime}")
    List<Double> select28y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x28' and create_time between #{startTime} and #{endTime}")
    List<Double> select28z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x29' and create_time between #{startTime} and #{endTime}")
    List<Double> select29x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x29' and create_time between #{startTime} and #{endTime}")
    List<Double> select29y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x29' and create_time between #{startTime} and #{endTime}")
    List<Double> select29z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x30' and create_time between #{startTime} and #{endTime}")
    List<Double> select30x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x30' and create_time between #{startTime} and #{endTime}")
    List<Double> select30y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x30' and create_time between #{startTime} and #{endTime}")
    List<Double> select30z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x31' and create_time between #{startTime} and #{endTime}")
    List<Double> select31x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x31' and create_time between #{startTime} and #{endTime}")
    List<Double> select31y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x31' and create_time between #{startTime} and #{endTime}")
    List<Double> select31z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x32' and create_time between #{startTime} and #{endTime}")
    List<Double> select32x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x32' and create_time between #{startTime} and #{endTime}")
    List<Double> select32y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x32' and create_time between #{startTime} and #{endTime}")
    List<Double> select32z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x33' and create_time between #{startTime} and #{endTime}")
    List<Double> select33x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x33' and create_time between #{startTime} and #{endTime}")
    List<Double> select33y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x33' and create_time between #{startTime} and #{endTime}")
    List<Double> select33z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x34' and create_time between #{startTime} and #{endTime}")
    List<Double> select34x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x34' and create_time between #{startTime} and #{endTime}")
    List<Double> select34y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x34' and create_time between #{startTime} and #{endTime}")
    List<Double> select34z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x35' and create_time between #{startTime} and #{endTime}")
    List<Double> select35x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x35' and create_time between #{startTime} and #{endTime}")
    List<Double> select35y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x35' and create_time between #{startTime} and #{endTime}")
    List<Double> select35z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x36' and create_time between #{startTime} and #{endTime}")
    List<Double> select36x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x36' and create_time between #{startTime} and #{endTime}")
    List<Double> select36y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x36' and create_time between #{startTime} and #{endTime}")
    List<Double> select36z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x37' and create_time between #{startTime} and #{endTime}")
    List<Double> select37x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x37' and create_time between #{startTime} and #{endTime}")
    List<Double> select37y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x37' and create_time between #{startTime} and #{endTime}")
    List<Double> select37z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x38' and create_time between #{startTime} and #{endTime}")
    List<Double> select38x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x38' and create_time between #{startTime} and #{endTime}")
    List<Double> select38y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x38' and create_time between #{startTime} and #{endTime}")
    List<Double> select38z(Date startTime, Date endTime);

    @Select("select a_x from position_value where hexadecimal = '0x39' and create_time between #{startTime} and #{endTime}")
    List<Double> select39x(Date startTime, Date endTime);

    @Select("select a_y from position_value where hexadecimal = '0x39' and create_time between #{startTime} and #{endTime}")
    List<Double> select39y(Date startTime, Date endTime);

    @Select("select a_z from position_value where hexadecimal = '0x39' and create_time between #{startTime} and #{endTime}")
    List<Double> select39z(Date startTime, Date endTime);
}
