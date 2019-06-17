package ejb.interfaces;

import java.io.Serializable;

public interface JMSHandlerManager {
    void sendMsg(Serializable obj, String ident);
    Serializable receiveMsg(String ident);
}
