package top.kaluna.pipelineMonitor.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.*;
import top.kaluna.pipelineMonitor.domain.QueryResulyRow;
import top.kaluna.pipelineMonitor.mapper.VariantPointMapper;
import top.kaluna.pipelineMonitor.resp.CommonResp;
import top.kaluna.pipelineMonitor.service.HistoryQueryService;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoryQueryController {


    @GetMapping("/list")
    @ResponseBody
    public CommonResp<List<QueryResulyRow>> findPage( String startTime,
                                                      String endTime) throws ParseException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");

        String resouce = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resouce);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = sqlSessionFactory.openSession(true);
        VariantPointMapper variantPointMapper = session.getMapper(VariantPointMapper.class);

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//        startTime = startTime.replace("Z", " UTC");
//        endTime = endTime.replace("Z", " UTC");
        Date startTime_parse = format.parse(startTime);
        Date endTime_parse = format.parse(endTime);
        CommonResp<List<QueryResulyRow>> resp = new CommonResp<>();
//        Integer total = variantPointMapper.selectTotal(startTime_parse,endTime_parse);
//        if(total%pageSize==0){
//            total = total/pageSize;
//        }else{
//            total = total/pageSize+1;
//        }
//        pageNum = (pageNum - 1) * pageSize;
//        List<QueryResulyRow> data = variantPointMapper.selectByPage(pageNum,pageSize,startTime_parse,endTime_parse);
        List<QueryResulyRow> data = variantPointMapper.selectByTime(startTime_parse,endTime_parse);
//        Map<String, Object> result = new HashMap<>();
//        result.put("date",data);
//        result.put("total",total);
        resp.setContent(data);
        return resp;
    }


}
