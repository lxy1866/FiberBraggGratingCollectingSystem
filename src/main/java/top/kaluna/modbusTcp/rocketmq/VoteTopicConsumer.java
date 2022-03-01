//package top.kaluna.modbusTcp.rocketmq;
//
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import top.kaluna.modbusTcp.websocket.WebSocketServer;
//
//
//import javax.annotation.Resource;
//
//
///**
// * @author Yuery
// * @date 2022/2/28/0028 - 14:07
// */
//@Service
//@RocketMQMessageListener(consumerGroup = "default",topic = "VOTE_TOPIC")
//public class VoteTopicConsumer implements RocketMQListener<MessageExt> {
//
//    private static final Logger LOG =  LoggerFactory.getLogger(VoteTopicConsumer.class);
//
//    @Resource
//    public WebSocketServer webSocketServer;
//    @Override
//    public void onMessage(MessageExt messageExt){
//        byte[] body = messageExt.getBody();
//        LOG.info("ROCKETMQ收到消息：{}", new String(body));
//        webSocketServer.sendInfo(new String(body));
//    }
//}
//
