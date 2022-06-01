//package top.kaluna.pipelineMonitor.biz;
//
//
//import org.junit.jupiter.api.Test;
//import top.kaluna.pipelineMonitor.domain.QueryTimeStartAndEnd;
//import top.kaluna.pipelineMonitor.util.DateUtil;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Yuery
// * @date 2022/5/27/0027 - 21:07
// */
//public class TestTime {
//    @Test
//    public void test(){
//        List<QueryTimeStartAndEnd> list = new ArrayList<>();
//        Date date = DateUtil.getStartTime();
//        for(int index = 0; index < 14; index++){
//            QueryTimeStartAndEnd queryTimeStartAndEnd = new QueryTimeStartAndEnd();
//            queryTimeStartAndEnd.setStart(DateUtil.GivenTimeLastNHoursStart(date,  12 * (index+1)));
//            queryTimeStartAndEnd.setEnd(DateUtil.GivenTimeLastNHoursStart(date, 12 * (index)));
//            list.add(queryTimeStartAndEnd);
//        }
//        System.out.println(list.toString());
//    }
//    @Test
//    public void test2(){
//        System.out.println(DateUtil.getStartTime());
//        System.out.println(DateUtil.getLastWeekMonday(DateUtil.getStartTime()));
//        System.out.println("节点1".split("节点")[1]);
//        System.out.println(DateUtil.getLastMonthStartTime());
//        System.out.println(DateUtil.getThisMonthStartTime());
//        System.out.println(DateUtil.GivenTimeLastNHoursStart(DateUtil.getThisWeekMonday(DateUtil.getStartTime()), 12));
//
//    }
//
//}
