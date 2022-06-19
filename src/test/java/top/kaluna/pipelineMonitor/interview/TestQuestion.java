//package top.kaluna.pipelineMonitor.interview;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.*;
//
///**
// * @author Yuery
// * @date 2022/6/2/0002 - 10:29
// */
//public class TestQuestion {
//    @Test
//    public void test(){
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        int total = in.nextInt();
//        int[] nums = new int[total];
//        int index  = 0;
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            nums[index] = in.nextInt();
//            index++;
//        }
//        List result = quchong(nums);
//        Object[] array = result.toArray();
//        for(int i = 0; i < array.length; i++){
//            System.out.println(array[i]);
//        }
//    }
//    public static List quchong(int[] nums){
//        Map map = new HashMap();
//        List result = new ArrayList();
//        for(int i = 0; i < nums.length; i++){
//            if(!map.containsKey(nums[i])){
//                map.put(nums[i],1);
//                result.add(nums[i]);
//            }else{
//                map.put(nums[i], (int)map.get(nums[i])+1);
//                if((int)map.get(nums[i]) != 2){
//                    int index = result.indexOf(nums[i]);
//                    result.remove(index);
//                }
//            }
//        }
//        Collections.sort(result);
//        return result;
//    }
//}
