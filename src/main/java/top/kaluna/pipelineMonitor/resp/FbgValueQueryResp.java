package top.kaluna.pipelineMonitor.resp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuery
 * @date 2022/3/21/0021 - 19:39
 */

public class FbgValueQueryResp {

    Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FbgValueQueryResp{");
        sb.append("map=").append(map);
        sb.append('}');
        return sb.toString();
    }
}
