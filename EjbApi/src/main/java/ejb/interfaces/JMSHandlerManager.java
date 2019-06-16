package ejb.interfaces;

public interface JMSHandlerManager {
    void sendMsg(String txt);
    String receiveMsg();
}
