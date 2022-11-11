package top.kaluna.modbusTcp.service;

import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.domain.PositionValue;
import top.kaluna.modbusTcp.domain.PositionValueExample;
import top.kaluna.modbusTcp.domain.TxtValue;
import top.kaluna.modbusTcp.mapper.PositionValueMapper;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PositionValueService {

    @Resource
    private PositionValueMapper positionValueMapper;

    public int insert(List<PositionValue> saveList) {
        int i = positionValueMapper.multipleInsert(saveList);
        return i;
    }
    public List<PositionValue> queryLast15(){
        List<PositionValue> list = new ArrayList<>();
        list = positionValueMapper.queryLast15();
        List<PositionValue> sorted = list.stream().sorted(Comparator.comparing(PositionValue::getHexadecimal)).collect(Collectors.toList());
        return sorted;
    }
    public List<PositionValue> query8Month29Day(){
        PositionValueExample example = new PositionValueExample();
        PositionValueExample.Criteria criteria = example.createCriteria();
        //日期是在8月29日
        LocalDateTime localDateTimeStart = LocalDateTime.of(2022,8,29,15,0,0);
        LocalDateTime localDateTimeEnd = LocalDateTime.of(2022,8,29,16,0,0);
        //获得 Instant
        Instant instantStart = Instant.ofEpochSecond(localDateTimeStart.toEpochSecond(ZoneOffset.ofHours(8)));
        Instant instantEnd = Instant.ofEpochSecond(localDateTimeEnd.toEpochSecond(ZoneOffset.ofHours(8)));
        // 获得 Date
        Date dateStart = Date.from(instantStart);
        Date dateEnd = Date.from(instantEnd);
        criteria.andCreateTimeBetween(dateStart, dateEnd);
        List<PositionValue> positionValues = positionValueMapper.selectByExample(example);
        positionValues.forEach((obj->{
            Date createTime = obj.getCreateTime();
            //不变的两个 分和秒
            int minutes = createTime.getMinutes();
            int seconds = createTime.getSeconds();
            LocalDateTime localDateTime = LocalDateTime.now();
            //当前date的分和秒重新赋值为minutes 和 seconds
            LocalDateTime localDateTime1 = localDateTime.withMinute(minutes);
            LocalDateTime localDateTime2 = localDateTime1.withSecond(seconds);
            Instant instant = Instant.ofEpochSecond(localDateTime2.toEpochSecond(ZoneOffset.ofHours(8)));
            Date date = Date.from(instant);
            obj.setCreateTime(date);
        }));
        return positionValues;
    }

}
