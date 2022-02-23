package top.kaluna.modbusTcp.domain;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Yuery
 * @date 2021/12/13/0013 - 10:02
 */
@Component
public class Message{

    public  Map<String, Float> messageMap;
    public Message(){

    }
    private static volatile Message instance;

    public static Message getInstance(){
        if(instance == null){
            synchronized (Message.class){
                if(instance == null){
                    instance = new Message();
                }
            }
        }
        return instance;
    }
    public Message(Map<String, Float> messageMap){
        this.messageMap = messageMap;
    }
    @Override
    public String toString(){
        return messageMap.toString();
    }

}
