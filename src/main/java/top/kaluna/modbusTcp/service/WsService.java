package top.kaluna.modbusTcp.service;

import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.kaluna.modbusTcp.websocket.WebSocketServer;
import top.kaluna.modbusTcp.websocket.WebSocketVibrationWaveServer;

import javax.annotation.Resource;

/**
 * @author Yuery
 * @date 2022/2/28/0028 - 13:53
 */
@Service
public class WsService {

    @Resource
    private WebSocketServer webSocketServer;
    @Resource
    private WebSocketVibrationWaveServer webSocketVibrationWaveServer;

    @Async
    public void sendInfo(String message, String logId) {
        //往所有的session推送消息
        MDC.put("LOG_ID", logId);
        webSocketServer.sendInfo(message);

    }

    @Async
    public void sendVibrationWaveInfo(String message, String logId) {
        //往所有的session推送消息
        MDC.put("LOG_ID", logId);
        webSocketVibrationWaveServer.sendInfo(message);
    }
}